package com.nefu.se.graduationprocessmanagement.controller;

import com.nefu.se.graduationprocessmanagement.component.CommonComponent;
import com.nefu.se.graduationprocessmanagement.component.EncryptorComponent;
import com.nefu.se.graduationprocessmanagement.entity.User;
import com.nefu.se.graduationprocessmanagement.exception.UnauthorizedException;
import com.nefu.se.graduationprocessmanagement.service.UserService;
import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private EncryptorComponent encryptorComponent;

    @PostMapping("/login")
    public ResultVO login(@RequestBody User user) {
        // 查找数据库中是否存在用户
        User u = Optional.ofNullable(userService.getUser(user.getNumber()))
                .orElseThrow(() -> {
                    // 抛出异常(统一异常处理)
                    log.debug("用户名密码错误");
                    return new UnauthorizedException(UnauthorizedException.LOGIN_ERROR);
                });
        // 判断用户名密码是否正确
        if (!passwordEncoder.matches(user.getPassword(), u.getPassword())) {
            return ResultVO.unAuthorizationResultVO()
                    .setMessage("用户名密码不正确");
        }
        // 设置 header
        commonComponent.setResponseHeader(encryptorComponent.userToJson(u));
        // 返回随机生成的10位随机字符
        return ResultVO.successResultVO()
                .setMessage("登陆成功")
                .setData(Map.of("role", getRoleHex(u.getRole())));
    }

    private String getRoleHex(int roleId) {
        StringBuffer sb = new StringBuffer(commonComponent.getRandomString());
        sb = sb.replace(3, 3, String.valueOf(roleId));
        log.info("sb ==>" + sb.toString());
        return sb.toString();
    }

    @RequestMapping(value = "exception", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT})
    public ResultVO getExp(HttpServletRequest request) {
        log.debug("exp");
        return (ResultVO) request.getAttribute("exception");
    }

}
