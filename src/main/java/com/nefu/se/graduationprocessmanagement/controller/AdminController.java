package com.nefu.se.graduationprocessmanagement.controller;

import com.nefu.se.graduationprocessmanagement.dto.TeacherDto;
import com.nefu.se.graduationprocessmanagement.entity.Teacher;
import com.nefu.se.graduationprocessmanagement.entity.User;
import com.nefu.se.graduationprocessmanagement.service.TeacherService;
import com.nefu.se.graduationprocessmanagement.service.UserService;
import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/api/sadmin")
@RestController
@Slf4j
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/teachers")
    public ResultVO initTeachers(@RequestBody List<TeacherDto> teacherDtos) {
        // TODO 对teacherDto 进行判断检验
        // 忽略 role 为 4 的
        teacherDtos = teacherDtos.stream()
                .filter(teacherDto -> {
                    log.debug("roleId ==>" + teacherDto.getRole());
                    log.debug("isTrue==>" + teacherDto.getRole().equals("4"));
                    return !teacherDto.getRole().equals("4");
                })
                .collect(Collectors.toList());
        log.debug("teacherDots ==>" + teacherDtos);
        // 去掉重复数据
        // 获取数据库已插入的所有数据
        List<User> users = userService.listAllUsers();
        // 数据库中存在用户的numbers
        List<String> allUserNumbers = users.stream().map(User::getNumber).collect(Collectors.toList());
        // 获取没有重复的的用户
        teacherDtos = teacherDtos.stream()
                .filter(teacherDto -> {
                    log.debug("isContains ==>" + allUserNumbers.contains(teacherDto.getNumber()));
                    return !allUserNumbers.contains(teacherDto.getNumber());
                })
                .collect(Collectors.toList());
        log.debug("teacherDots ==>" + teacherDtos);

        // 将数据插入到数据库中
        // TODO 测试发生异常事务回滚
        teacherDtos.forEach((teacherDto -> {
            // 插入到User表中
            User user = new User();
            user.setNumber(teacherDto.getNumber());
            user.setName(teacherDto.getName());
            user.setPassword(passwordEncoder.encode(teacherDto.getNumber()));
            user.setRole(Integer.valueOf(teacherDto.getRole()));
            userService.addUser(user);
            // 插入到teacher表中
            Teacher teacher = new Teacher();
            // 复用id
            teacher.setId(user.getId());
            teacher.setTitle(teacherDto.getTitle());
            teacher.setQuantity((short) 0);
            teacherService.addTeacher(teacher);
        }));

        List<Teacher> teachers = teacherService.listTeachers();

        return ResultVO.successResultVO()
                .setMessage("初始化成功")
                .setData(Map.of("teachers", teachers));
    }
}
