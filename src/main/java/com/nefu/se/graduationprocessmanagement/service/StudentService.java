package com.nefu.se.graduationprocessmanagement.service;

import com.nefu.se.graduationprocessmanagement.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public int deleteStudent(String sid) {
        return studentMapper.deleteById(sid);
    }
}
