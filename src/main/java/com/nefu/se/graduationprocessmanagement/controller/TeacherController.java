package com.nefu.se.graduationprocessmanagement.controller;

import com.nefu.se.graduationprocessmanagement.component.CommonComponent;
import com.nefu.se.graduationprocessmanagement.service.TeacherService;
import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/teachers")
@Api("教师角色接口")
public class TeacherController {
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private TeacherService teacherService;

    @ApiOperation("修改教师描述信息")
    @PatchMapping("/info")
    public ResultVO updateInfo(@RequestBody Map<String, String> map) {
        String title = map.get("title");
        String description = map.get("description");
        String uId = commonComponent.getUserId();
        // TODO 批量更新多个字段
        teacherService.updateTitle(title, uId);
        return ResultVO.successResultVO(Map.of());
    }
}
