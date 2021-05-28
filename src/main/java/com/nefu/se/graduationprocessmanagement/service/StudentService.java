package com.nefu.se.graduationprocessmanagement.service;

import com.nefu.se.graduationprocessmanagement.dto.StudentInfoDTO;
import com.nefu.se.graduationprocessmanagement.entity.Student;
import com.nefu.se.graduationprocessmanagement.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public int deleteStudent(Long sid) {
        return studentMapper.deleteById(sid);
    }

    public Integer getCount() {
        return studentMapper.count();
    }

    public List<StudentInfoDTO> listStudentInfos() {
        return studentMapper.listStudentInfos();
    }

    public void insert(Student student) {
        studentMapper.insert(student);
    }
}
