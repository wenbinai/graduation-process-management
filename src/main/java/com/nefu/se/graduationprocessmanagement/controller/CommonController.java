package com.nefu.se.graduationprocessmanagement.controller;

import com.nefu.se.graduationprocessmanagement.entity.Teacher;
import com.nefu.se.graduationprocessmanagement.service.TeacherService;
import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/common")
public class CommonController {
    @Autowired
    TeacherService teacherService;
    // todo 添加分页功能
    @GetMapping("/teachers")
    public ResultVO listAllTeachers() {
        List<Teacher> teachers = teacherService.listTeachers();
        return ResultVO.successResultVO()
                .setData(Map.of("teachers", teachers));
    }
}
