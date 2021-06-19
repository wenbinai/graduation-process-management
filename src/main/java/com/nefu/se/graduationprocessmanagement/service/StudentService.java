package com.nefu.se.graduationprocessmanagement.service;

import com.nefu.se.graduationprocessmanagement.dto.StudentInfoDTO;
import com.nefu.se.graduationprocessmanagement.entity.Student;
import com.nefu.se.graduationprocessmanagement.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Slf4j
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Long, Integer> ho;

    // 初始化一个HashOperations操作对象，避免反复创建
    @PostConstruct
    public void init() {
        ho = redisTemplate.opsForHash();
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
