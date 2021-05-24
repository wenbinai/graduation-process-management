package com.nefu.se.graduationprocessmanagement.controller;

import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/director/tasks")
@Api("任务接口")
public class TaskController {
    @ApiOperation("创建选择导师任务")
    @PostMapping("choice")
    public ResultVO createTask() {
        // 获取所有老师所带的学生数目
        // 获取所有学生数目
        // 如果不同,
        // 相同, 创建task
        return ResultVO.successResultVO(Map.of());
    }
}
