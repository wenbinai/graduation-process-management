package com.nefu.se.graduationprocessmanagement.service;

import com.nefu.se.graduationprocessmanagement.common.Constant;
import com.nefu.se.graduationprocessmanagement.common.MyException;
import com.nefu.se.graduationprocessmanagement.dto.StudentInfoDTO;
import com.nefu.se.graduationprocessmanagement.entity.Student;
import com.nefu.se.graduationprocessmanagement.entity.Teacher;
import com.nefu.se.graduationprocessmanagement.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@Transactional
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, Long, Integer> ho;
    private String key = Constant.Redis.TEACHER_QUANTITY_KEY;
    public ArrayBlockingQueue<Long> queue = new ArrayBlockingQueue<Long>(100);
    // 避免重复选择
    private ConcurrentHashMap<Long, Integer> map = new ConcurrentHashMap<>();

    // 初始化一个HashOperations操作对象，避免反复创建
    @PostConstruct
    public void init() {
        ho = redisTemplate.opsForHash();
    }

    public Student getStudentById(Long id) {
        return studentMapper.selectById(id);
    }

    // 学生选择导师
    public boolean chooseMentor(Long sid, Long tid) {
        // 1. 判断是否选择了导师
        if (map.get(sid) != null) return false;
        else map.put(sid, 1);
        Student student = getStudentById(sid);
        if (student == null || student.getTeacherId() != null) {
            return false;
        }
        // 2. Redis扣数量
        boolean choose = choose(student, tid);
        // TODO 消息队列异步扣减
        return choose;
    }

    // 初始化导师的数量
    public void initTeacherQuantity(Teacher teacher) {
        ho.put(key, teacher.getId(), teacher.getQuantity());
    }

    public void initTeacherQuantity(Long id, Integer quantity) {
        ho.put(key, id, quantity);
    }

    // 学生选择导师具体过程
    public boolean choose(Student student, Long tid) {
//        if (!map.get(student.getId()).equals(1)) return false;
//        else map.put(sid, 1);
        Long stid = student.getTeacherId();
        if (student.getTeacherId() != null) return false;
        if (!ho.hasKey(key, tid)) {
            log.debug("已达到导师上限, 请选择其他导师");
            return false;
        }
        // increment()/set() 单线程
        // get() 多线程
        // TODO 使用布隆过滤器/Set过滤重复请求
        if (ho.increment(key, tid, -1) < 0) {
            log.debug("已达到导师上限, 请选择其他导师");
            return false;
        }
        // 上面可以防止超卖(只有指定count数量的线程才能进入下面)

        // <==================多线程更新数据库============>
        log.debug("进入studentId: {}", student.getId());
        try {
            queue.put(student.getId());
        } catch (InterruptedException e) {
            return false;
        }
        Teacher teacher = teacherService.getTeacherById(tid);
        // 抢购成功, 写进数据库 TODO 使用消息队列异步完成
        // 悲观锁方案(for update)   TODO 乐观锁方案
        int quantity = teacherService.getQuantityByIdForUpdate(tid);
        if (quantity <= 0) {
            return false;
        }
        try {
            int update = teacherService.updateQuantity(tid, quantity - 1);
            if (update <= 0) {
                return false;
            } else {
                insertTeacherId(student, tid);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // 插入数据库中
    private void insertTeacherId(Student student, Long tid) {
        // TODO 解决用户多次设置教师 防止单个用户请求多次
        if (student.getTeacherId() != null) return;
        student.setTeacherId(tid);
        studentMapper.updateById(student);
        log.debug("插入成功ID: {}", student.getId());
    }


    @CacheEvict(value = "user", key = "#sid")
    public int deleteStudent(Long sid) {
        // TODO: 删除用户表中的数据
        return studentMapper.deleteById(sid);
    }

    public Integer getCount() {
        return studentMapper.count();
    }

    @Cacheable(value = "users")
    public List<StudentInfoDTO> listStudentInfos() {
        log.debug("===>call listStudentInfos()<===");
        int size = studentMapper.listStudentInfos().size();
        log.debug("size: {}", size);
        return studentMapper.listStudentInfos();
    }

    @CachePut(value = "user", key = "#student.id")
    @CacheEvict(value = "users", allEntries = true)
    public void insert(Student student) {
        studentMapper.insert(student);
    }
}
