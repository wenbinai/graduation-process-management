package com.nefu.se.graduationprocessmanagement.controller;

import com.nefu.se.graduationprocessmanagement.entity.Teacher;
import com.nefu.se.graduationprocessmanagement.service.TeacherService;
import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api("登陆用户通用接口")
@RestController
@RequestMapping("/api/common")
public class CommonController {
    @Autowired
    TeacherService teacherService;

    // todo 添加分页, 缓存功能
    @ApiOperation("获取所有教师信息")
    @GetMapping("/teachers")
    public ResultVO listAllTeachers() {
        List<Teacher> teachers = teacherService.listTeachers();
        return ResultVO.successResultVO(Map.of("teachers", teachers));
    }

    @ApiOperation("获取所有学生的基本信息")
    @GetMapping("/students")
    public ResultVO listAllStudents() {
        return null;
    }

    /**
     * 思考？ 修改密码涉及用户是否需要重新登陆
     *
     * @return
     */
    @ApiOperation("修改密码")
    @PatchMapping("/password")
    public ResultVO updatePassword(@RequestParam("authorizaiton") String authorization) {
        return null;
    }

    @ApiOperation("获取所有学生数量 ")
    @GetMapping("/count")
    public ResultVO getStudentCount() {
        return null;
    }
}
