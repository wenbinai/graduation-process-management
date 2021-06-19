package com.nefu.se.graduationprocessmanagement.service;

import com.nefu.se.graduationprocessmanagement.dto.StudentInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Test
    public void test_delete_student() {

    }

    @Test
    public void test_list_all_student() {
        List<StudentInfoDTO> studentInfoDTOS = studentService.listStudentInfos();
        studentInfoDTOS.stream()
                .forEach(studentInfoDTO -> System.out.println(studentInfoDTO.getNumber()));
    }

    @Test
    public void test_insert_student() {

    }

}
