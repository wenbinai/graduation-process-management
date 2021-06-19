package com.nefu.se.graduationprocessmanagement.controller;

import com.nefu.se.graduationprocessmanagement.service.StudentService;
import com.nefu.se.graduationprocessmanagement.service.TeacherService;
import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api("学生选择导师接口")
@RestController
@RequestMapping("/api/student")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/mentor/{tid}")
    public ResultVO chooseMentor(
            @RequestAttribute("id") Long id,
            @PathVariable(value = "tid") Long tid) {
        // TODO 限流: 每秒进入15个用户
        boolean flag = studentService.chooseMentor(id, tid);
        if (flag) {
            Integer quantity = teacherService.getQuantityById(tid);
            return ResultVO.success(Map.of("quantity", quantity));
        } else {
            return ResultVO.fail(400, "选择失败");
        }
    }
}
