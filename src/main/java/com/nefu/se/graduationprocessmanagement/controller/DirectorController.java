package com.nefu.se.graduationprocessmanagement.controller;

import com.nefu.se.graduationprocessmanagement.entity.User;
import com.nefu.se.graduationprocessmanagement.exception.UnauthorizedException;
import com.nefu.se.graduationprocessmanagement.service.UserService;
import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/director")
@Slf4j
public class DirectorController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * TODO
     * 导入学生功能
     *
     * @return
     */
    @PostMapping("/students")
    public ResultVO initStudents() {
        return null;
    }

    /**
     * 修改指定教师的角色权限
     *
     * @param uid
     * @return
     */
    @PatchMapping("/teachers/{uid}/role")
    public ResultVO updateRole(@PathVariable(value = "uid") String uid) {
        // 判断uid指定的用户是否存在
        User userFromDB = Optional.ofNullable(userService.getUserById(uid))
                .orElseThrow(() -> {
                    return new UnauthorizedException("用户不存在");
                });
        // 判断是否为教师
        Integer role = userFromDB.getRole();
        if (role != 2) {
            return ResultVO.failClientResultVO()
                    .setMessage("用户身份不正确");
        }
        // 修改权限为4 todo 判断操作是否成功
        userService.updateRole(uid);
        // 返回角色
        return ResultVO.successResultVO()
                .setData(Map.of("role", 4));
    }

    /**
     * TODO
     * 修改教师个人信息
     *
     * @param uid
     * @return
     */
//    @PatchMapping("/teachers/{uid}/info")
//    public ResultVO updateInfo(@PathVariable(value = "uid") String uid) {
//        // 判断uid指定的用户是否存在
//        // 判断是否为教师
//        // 更新教师的简介信息
//    }

    /**
     * 重置教师密码
     */
    @PutMapping("/teachers/{uid}/password")
    public ResultVO updatePassword(@PathVariable(value = "uid") String uid) {
        // 判断uid指定的用户是否存在
        User userFromDB = Optional.ofNullable(userService.getUserById(uid))
                .orElseThrow(() -> {
                    return new UnauthorizedException("用户不存在");
                });
        // 判断是否为教师
        Integer role = userFromDB.getRole();
        if (role != 2) {
            return ResultVO.failClientResultVO()
                    .setMessage("用户身份不正确");
        }
        // 重置密码为number
        String newPassword = passwordEncoder.encode(userFromDB.getNumber());
        log.debug("newPassword==>" + newPassword);
        userService.updatePasswordById(uid, newPassword);
        return ResultVO.successResultVO()
                .setMessage("更改密码成功");
    }

}
