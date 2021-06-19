package com.nefu.se.graduationprocessmanagement.controller;

import com.nefu.se.graduationprocessmanagement.entity.Teacher;
import com.nefu.se.graduationprocessmanagement.service.TeacherService;
import com.nefu.se.graduationprocessmanagement.service.UserService;
import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController("/api/student/")
@Slf4j
public class StudentController {
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;

    @PostMapping("mentor/{tid}")
    public ResultVO chooseMentor(
            @RequestAttribute("uid") Long uid,
            @PathVariable(value = "tid") Long tid) {
        // 限流: 每秒进入15个用户

        // TODO 加入缓存
        Teacher teacher = teacherService.getTeacherById(tid);
        // 使用乐观锁减少库存
        Integer quantity = teacher.getQuantity();
        return ResultVO.success(Map.of("quantity", quantity));
    }
}
