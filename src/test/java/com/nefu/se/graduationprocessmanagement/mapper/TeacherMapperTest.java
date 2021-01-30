package com.nefu.se.graduationprocessmanagement.mapper;

import com.nefu.se.graduationprocessmanagement.entity.Teacher;
import com.nefu.se.graduationprocessmanagement.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class TeacherMapperTest {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private TeacherService teacherService;

    @Test
    public void insertTeacher_test() {
        Teacher teacher = new Teacher();
        teacher.setTitle("副教授");
        teacher.setDescription("希望指导做小程序相关的毕业设计");
        teacher.setGroup((short) 1);
        teacher.setQuantity((short) 10);
        teacherMapper.insert(teacher);
    }

    /**
     * 测试插入数据的时候发生异常
     * 如果没有加入事务, 发生异常前, 数据正常插入
     * 异常后, 数据不能插入到数据库中
     *
     * @throws Exception
     */
    @Test
    public void insertNoTransactional_test() throws Exception {
        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                throw new Exception("插入数据出现异常");
            }
            Teacher teacher = new Teacher();
            teacher.setTitle("教授");
            teacher.setDescription("希望指导做小程序相关的毕业设计");
            teacher.setGroup((short) 1);
            teacher.setQuantity((short) 10);
            teacherMapper.insert(teacher);
        }
    }

    /**
     * 测试加入事务后, 插入数据发生异常的情况
     *
     * @throws Exception
     */
    @Test
    public void insertWithTransactional_test() throws Exception {
        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                throw new Exception("插入数据出现异常");
            }
            Teacher teacher = new Teacher();
            teacher.setTitle("讲师");
            teacher.setDescription("希望指导做JavaWeb相关的毕业设计");
            teacher.setGroup((short) 1);
            teacher.setQuantity((short) 10);
            teacherService.addTeacher(teacher);
        }
    }

    /**
     * 测试加入事务后, 插入数据发生异常的情况
     *
     * @throws Exception
     */
    @Test
    public void insertWithTransactional_test2() {
        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                Teacher teacher = new Teacher();
                teacher.setTitle("讲师3");
                teacher.setDescription("希望指导做JavaWeb相关的毕业设计");
                teacher.setGroup((short) 1);
                teacher.setQuantity((short) 10);
                teacherService.addTeacher(teacher);
                throw new RuntimeException("插入数据出现异常");
            }
            Teacher teacher = new Teacher();
            teacher.setTitle("讲师2");
            teacher.setDescription("希望指导做JavaWeb相关的毕业设计");
            teacher.setGroup((short) 1);
            teacher.setQuantity((short) 10);
            teacherService.addTeacher(teacher);
        }
    }

    @Test
    public void insertWithTransactional_test3() {
        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                try {
                    Teacher teacher = new Teacher();
                    teacher.setTitle("讲师4");
                    teacher.setDescription("希望指导做JavaWeb相关的毕业设计");
                    teacher.setGroup((short) 1);
                    teacher.setQuantity((short) 10);
                    teacherService.addTeacher(teacher);
                    System.out.println(1 / 0);
                } catch (Exception e) {
                    throw new RuntimeException("插入数据出现异常");
                }
            }
            Teacher teacher = new Teacher();
            teacher.setTitle("讲师2");
            teacher.setDescription("希望指导做JavaWeb相关的毕业设计");
            teacher.setGroup((short) 1);
            teacher.setQuantity((short) 10);
            teacherService.addTeacher(teacher);
        }
    }

}
