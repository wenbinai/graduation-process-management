//package com.nefu.se.graduationprocessmanagement.service;
//
//import com.nefu.se.graduationprocessmanagement.common.Constant;
//import com.nefu.se.graduationprocessmanagement.dto.StudentInfoDTO;
//import com.nefu.se.graduationprocessmanagement.entity.Student;
//import com.nefu.se.graduationprocessmanagement.entity.Teacher;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeFormatterBuilder;
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@SpringBootTest
//@Slf4j
//public class StudentServiceTest {
//    @Autowired
//    private StudentService studentService;
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    @Test
//    public void test_local_date_time_parse() {
//        String text = "2021-06-09T16:00:00.000Z";
//        int i = text.indexOf('.');
//        text = text.substring(0, i);
//        System.out.println(text);
//        LocalDateTime parse = LocalDateTime.parse(text);
//        System.out.println(parse);
//    }
//
//    @Test
//    public void test_init_teacher_quantity() {
//        Teacher teacher = new Teacher();
//        teacher.setId(1398138396169797633L);
//        teacher.setQuantity(10);
//        studentService.initTeacherQuantity(teacher);
//        // 查询
//        HashOperations<String, Long, Integer> ho = redisTemplate.opsForHash();
//        log.debug("插入的数量: {}", ho.get(Constant.Redis.TEACHER_QUANTITY_KEY, teacher.getId()));
//    }
//
//    @Test
//    public void test_choose_mentor() throws InterruptedException {
//        // 测试是否会重复插入到数据库中
//        Long[] sids01 = new Long[]{
//                1400073677450633218L,
//                1400073677832314881L,
//                1400073678222385154L,
//                1400073677450633218L,
//                1400073677832314881L,
//                1400073678222385154L,
//                1400073677450633218L,
//                1400073677832314881L,
//                1400073678222385154L,
//                1400073677450633218L,
//                1400073677832314881L,
//                1400073678222385154L,
//                1400073677450633218L,
//                1400073677832314881L,
//                1400073678222385154L,
//                1400073677450633218L,
//                1400073677832314881L,
//                1400073678222385154L,
//                1400073677450633218L,
//                1400073677832314881L,
//                1400073678222385154L,
//                1400073677450633218L,
//                1400073677832314881L,
//                1400073678222385154L
//        };
//        // 模拟并发抢购人数
//        Long[] sids = new Long[]{
//                1398205266902200322L,
//                1400073668902641665L,
//                1400073669305294850L,
//                1400073669691170817L,
//                1400073670081241090L,
//                1400073670467117057L,
//                1400073670865575938L,
//                1400073671255646210L,
//                1400073671645716482L,
//                1400073672039981058L,
//                1400073672425857026L,
//                1400073672811732993L,
//                1400073673197608961L,
//                1400073673596067841L,
//                1400073673977749506L,
//                1400073674359431170L,
//                1400073674749501441L,
//                1400073675135377410L,
//                1400073675521253378L,
//                1400073675911323650L,
//                1400073676297199617L
//        };
//        int count = sids01.length;
//        CountDownLatch latch = new CountDownLatch(count);
//        AtomicInteger logic = new AtomicInteger(0);
//        for (int i = 0; i < count; i++) {
//            int finalI = i;
//            new Thread(() -> {
//                if (studentService.chooseMentor(sids01[finalI], 1398138396169797633L)) {
//                    logic.incrementAndGet();
//                }
//                latch.countDown();
//            }, i + "").start();
//        }
//        latch.await();
////        log.debug("队列大小: {}", studentService.queue.size());
////        studentService.queue.stream()
////                .forEach((item) -> {
////                    System.out.println("queue中的id: " + item);
////                });
//        HashOperations<String, Long, Integer> ho = redisTemplate.opsForHash();
//        log.debug("队列queue: {}", studentService.queue);
//        log.debug("商品被抢购数量：{}", logic.get());
//        // redis中商品的最终数量(负数)，未抢到数量；加上商品上架总数，抢到数量；等于所有参与抢购人数，即创建线程数
//        log.debug("商品总抢购人数：{}", Math.abs(ho.get(Constant.Redis.TEACHER_QUANTITY_KEY, 1406071951722053633L) + 10));
//
//    }
//
//
//    @Test
//    public void test_choose() throws InterruptedException {
//        // 模拟并发抢购人数
//        Long[] sids = new Long[]{
//                1398205266902200322L,
//                1400073668902641665L,
//                1400073669305294850L,
//                1400073669691170817L,
//                1400073670081241090L,
//                1400073670467117057L,
//                1400073670865575938L,
//                1400073671255646210L,
//                1400073671645716482L,
//                1400073672039981058L,
//                1400073672425857026L,
//                1400073672811732993L,
//                1400073673197608961L,
//                1400073673596067841L,
//                1400073673977749506L,
//                1400073674359431170L,
//                1400073674749501441L,
//                1400073675135377410L,
//                1400073675521253378L,
//                1400073675911323650L,
//                1400073676297199617L
//        };
//        int count = sids.length;
//        CountDownLatch latch = new CountDownLatch(count);
//        AtomicInteger logic = new AtomicInteger(0);
//        Student[] students = new Student[count];
//
//        for (int i = 0; i < sids.length; i++) {
//            students[i] = new Student();
//            students[i].setId(sids[i]);
//        }
//        for (int i = sids.length; i < count; i++) {
//            students[i] = new Student();
//            students[i].setId(sids[0]);
//        }
//        for (int i = 0; i < count; i++) {
//            int finalI = i;
//            new Thread(() -> {
//                log.info("请求student: {}", finalI);
//                if (studentService.choose(students[finalI], 1406071951722053633L)) {
//                    logic.incrementAndGet();
//                }
//                latch.countDown();
//            }, i + "").start();
//        }
//        latch.await();
//        HashOperations<String, Long, Integer> ho = redisTemplate.opsForHash();
//        log.debug("商品被抢购数量：{}", logic.get());
//        // redis中商品的最终数量(负数)，未抢到数量；加上商品上架总数，抢到数量；等于所有参与抢购人数，即创建线程数
//        log.debug("商品总抢购人数：{}", Math.abs(ho.get(Constant.Redis.TEACHER_QUANTITY_KEY, 1406071951722053633L) + 10));
//    }
//
//    @Test
//    public void test_delete_student() {
//
//    }
//
//    @Test
//    public void test_list_all_student() {
//        List<StudentInfoDTO> studentInfoDTOS = studentService.listStudentInfos();
//        studentInfoDTOS.stream()
//                .forEach(studentInfoDTO -> System.out.println(studentInfoDTO.getNumber()));
//    }
//
//    @Test
//    public void test_insert_student() {
//
//    }
//
//}
