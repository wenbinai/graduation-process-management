package com.nefu.se.graduationprocessmanagement.controller;

import com.nefu.se.graduationprocessmanagement.dto.StudentInfoDTO;
import com.nefu.se.graduationprocessmanagement.dto.TeacherInfoDTO;
import com.nefu.se.graduationprocessmanagement.entity.User;
import com.nefu.se.graduationprocessmanagement.service.StudentService;
import com.nefu.se.graduationprocessmanagement.service.TeacherService;
import com.nefu.se.graduationprocessmanagement.service.UserService;
import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api("登陆用户通用接口")
@RestController
@RequestMapping("/api/common")
@Slf4j
public class CommonController {
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private StudentService studentService;

    // todo 添加缓存功能
    @ApiOperation("获取所有教师信息")
    @GetMapping("/teachers")
    public ResultVO listAllTeachers() {
        List<TeacherInfoDTO> teachers = teacherService.listTeacherInfos();
        return ResultVO.success(Map.of("teachers", teachers));
    }

//    @ApiOperation("获取单个教师信息")
//    @GetMapping("/teachers/{tid}")
//    public ResultVO listTeacherInfo(@PathVariable(value = "tid") String tid) {
//        TeacherInfoDTO teacherInfoDTO = teacherService.listTeacherInfo(tid);
//        return ResultVO.success(Map.of("teacher", teachers));
//    }

    // TODO 获取单个学生的信息


    // todo 添加分页, 缓存功能
    @ApiOperation("获取所有学生的基本信息")
    @GetMapping("/students")
    public ResultVO listAllStudents() {
        List<StudentInfoDTO> students = studentService.listStudentInfos();
        return ResultVO.success(Map.of("students", students));
    }

    /**
     * 思考？ 修改密码涉及用户是否需要重新登陆
     *
     * @return
     */
    @ApiOperation("修改密码")
    @PatchMapping("/password")
    public ResultVO updatePassword(@RequestBody Map<String, String> data,
                                   @RequestAttribute("id") Long id) {
        String newPassword = data.get("password");
        newPassword = passwordEncoder.encode(newPassword);
        userService.updatePasswordById(id, newPassword);
        return ResultVO.success(Map.of());
    }

    @ApiOperation("获取所有学生数量 ")
    @GetMapping("/students/count")
    public ResultVO getStudentCount() {
        Integer count = studentService.getCount();
        return ResultVO.success(Map.of("count", count));
    }

    @ApiOperation("查看用户个人信息")
    @GetMapping("/info")
    public ResultVO getUserInfo(@RequestAttribute("id") Long id) {
        log.debug("id: {}", id);
        User user = userService.getUserById(id);
        String name = user.getName();
        String number = user.getNumber();
        return ResultVO.success(Map.of("name", name, "number", number));
    }
}
