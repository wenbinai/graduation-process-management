package com.nefu.se.graduationprocessmanagement.controller;

import com.nefu.se.graduationprocessmanagement.service.TeacherService;
import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/teachers")
@Api("教师角色接口")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation("修改教师描述信息")
    @PatchMapping("/info")
    public ResultVO updateInfo(@RequestBody Map<String, Object> map,
                               @RequestAttribute("id") long id) {
        teacherService.updateInfo(map, id);
        return ResultVO.success(Map.of());
    }
}
