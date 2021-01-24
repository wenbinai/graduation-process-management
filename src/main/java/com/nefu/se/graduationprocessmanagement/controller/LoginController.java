package com.nefu.se.graduationprocessmanagement.controller;

import com.nefu.se.graduationprocessmanagement.entity.User;
import com.nefu.se.graduationprocessmanagement.service.UserService;
import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController("/api")
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResultVO login(@RequestBody User user) {
        return userService.doLogin(user);
    }

    @GetMapping("/test")
    public ResultVO test() {
        log.info("进入方法内--");
        return ResultVO.successResultVO();
    }
}
