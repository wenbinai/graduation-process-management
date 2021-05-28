package com.nefu.se.graduationprocessmanagement.controller;

import com.nefu.se.graduationprocessmanagement.dto.TeacherDTO;
import com.nefu.se.graduationprocessmanagement.entity.Teacher;
import com.nefu.se.graduationprocessmanagement.entity.User;
import com.nefu.se.graduationprocessmanagement.service.TeacherService;
import com.nefu.se.graduationprocessmanagement.service.UserService;
import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(value = "处理管理员相关请求", tags = {"Authorization, Admin"})
@RequestMapping("/api/sadmin")
@RestController
@Transactional
@Slf4j
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @ApiOperation("导入教师名单")
    @PostMapping("/teachers")
    public ResultVO initTeachers(@RequestBody List<TeacherDTO> teacherDtos) {
        // 获取role为2
        teacherDtos = teacherDtos.stream()
                .filter(teacherDto -> {
                    log.debug("roleId ==>" + teacherDto.getRole());
                    log.debug("isTrue==>" + teacherDto.getRole().equals("4"));
                    return teacherDto.getRole().equals("2");
                })
                .collect(Collectors.toList());
        log.debug("teacherDots ==>" + teacherDtos);
        // 去掉重复数据 (number 加上唯一索引)
//        // 获取数据库已插入的所有数据
//        List<User> users = userService.listAllUsers();
//        // 数据库中存在用户的numbers
//        List<String> allUserNumbers = users.stream().map(User::getNumber).collect(Collectors.toList());
//        // 获取没有重复的的用户
//        teacherDtos = teacherDtos.stream()
//                .filter(teacherDto -> {
//                    log.debug("isContains ==>" + allUserNumbers.contains(teacherDto.getNumber()));
//                    return !allUserNumbers.contains(teacherDto.getNumber());
//                })
//                .collect(Collectors.toList());
//        log.debug("teacherDots ==>" + teacherDtos);

        // 将数据插入到数据库中
        // TODO 测试发生异常事务回滚
        for (TeacherDTO teacherDto : teacherDtos) {
            // 插入到User表中
            User user = new User();
            user.setNumber(teacherDto.getNumber());
            user.setName(teacherDto.getName());
            user.setPassword(passwordEncoder.encode(teacherDto.getNumber()));
            user.setRole(Integer.valueOf(teacherDto.getRole()));
            try {
                userService.addUser(user);
            } catch (Exception e) {
                continue;
            }
            // 插入到teacher表中
            Teacher teacher = new Teacher();
            // 复用id
            teacher.setId(user.getId());
            teacher.setTitle(teacherDto.getTitle());
            teacher.setQuantity(0);
            teacherService.addTeacher(teacher);
        }
        List<TeacherDTO> teacherDTOs = teacherService.listTeachers();

        return ResultVO.success(Map.of("teachers", teacherDTOs));
    }
}
