package com.nefu.se.graduationprocessmanagement.controller;

import com.nefu.se.graduationprocessmanagement.dto.TeacherDto;
import com.nefu.se.graduationprocessmanagement.dto.TeacherQuantity;
import com.nefu.se.graduationprocessmanagement.entity.Teacher;
import com.nefu.se.graduationprocessmanagement.entity.User;
import com.nefu.se.graduationprocessmanagement.common.UnauthorizedException;
import com.nefu.se.graduationprocessmanagement.service.StudentService;
import com.nefu.se.graduationprocessmanagement.service.TeacherService;
import com.nefu.se.graduationprocessmanagement.service.UserService;
import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api("系主任相关接口")
@RestController
@RequestMapping("/api/director")
@Slf4j
public class DirectorController {
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private StudentService studentService;


    /**
     * 统一更新全部教师带学生数目
     *
     * @param teacherQuantities
     * @return
     */
    @ApiOperation("统一更新全部教师带学生数目")
    @PatchMapping("/teachers/quantity")
    public ResultVO updateQuantity(@RequestBody List<TeacherQuantity> teacherQuantities) {
        teacherQuantities.stream()
                .forEach((teacherQuantity) -> {
                    teacherService.updateQuantity(teacherQuantity.getId(), teacherQuantity.getQuantity());
                });
        List<Teacher> teachers = teacherService.listTeachers();
        log.debug("after update teachers ==>" + teachers);
        return ResultVO.successResultVO(Map.of("teachers", teachers));
    }

    /**
     * TODO
     * 导入学生功能
     *
     * @return
     */
    @ApiOperation("批量导入学生名单")
    @PostMapping("/students")
    public ResultVO initStudents() {

        return null;
    }

    /**
     * 修改指定教师的角色权限 (只能修改教师的权限为..., 不能修改学生)
     *
     * @param uid
     * @return
     */
    @ApiOperation("修改教师权限")
    @PatchMapping("/teachers/{uid}/role")
    public ResultVO updateRole(@PathVariable(value = "uid") String uid) {
        log.debug("进入==>");
        // 判断uid指定的用户是否存在
        User userFromDB = Optional.ofNullable(userService.getUserById(uid))
                .orElseThrow(() -> {
                    return new UnauthorizedException("用户不存在");
                });
        // 判断是否为教师
        log.debug("userFromDB==>", userFromDB);
        Integer role = userFromDB.getRole();
        if (role != 2) {
            return ResultVO.fail(403, "无权限");
        }
        // 修改权限为4 todo 判断操作是否成功
        userService.updateRole(uid);
        log.debug("修改成功");
        // 返回角色
        return ResultVO.successResultVO(Map.of("role", 4));
    }

    /**
     * TODO
     * 修改个人信息
     *
     * @param uid
     * @return
     */
    @ApiOperation("更新教师基本信息, 不包括重置密码")
    @PatchMapping("/teachers/{uid}/info")
    public ResultVO updateInfo(@PathVariable(value = "uid") String uid,
                               @RequestBody Map<String, String> map) {
        // 判断uid指定的用户是否存在
        User userFromDB = Optional.ofNullable(userService.getUserById(uid))
                .orElseThrow(() -> new UnauthorizedException("用户不存在"));
        // 判断是否为教师
        log.debug("userFromDB==>" + userFromDB);
//        Integer role = userFromDB.getRole();
//        if (role != 2) {
//            return ResultVO.failClientResultVO()
//                    .setMessage("用户身份不正确");
//        }
        log.debug("map==>" + map);
        // 更新教师的简介信息
        String name = map.get("name");
        log.debug("name==>" + name);
        String title = map.get("title");
        log.debug("title==>" + title);
        teacherService.updateTitle(title, uid);
        return ResultVO.successResultVO(Map.of());
    }

    /**
     * 重置密码功能
     */
    @ApiOperation("重置教师密码")
    @PutMapping("/teachers/{uid}/password")
    public ResultVO updatePassword(@PathVariable(value = "uid") String uid) {
        // 判断uid指定的用户是否存在
        User userFromDB = Optional.ofNullable(userService.getUserById(uid))
                .orElseThrow(() -> new UnauthorizedException("用户不存在"));
//        // 判断是否为教师
//        Integer role = userFromDB.getRole();
//        if (role != 2) {
//            return ResultVO.failClientResultVO()
//                    .setMessage("用户身份不正确");
//        }
        // 重置密码为number
        String newPassword = passwordEncoder.encode(userFromDB.getNumber());
        log.debug("newPassword==>" + newPassword);
        userService.updatePasswordById(uid, newPassword);
        return ResultVO.successResultVO(Map.of());
    }

    /**
     * 删除指定教师
     */
    @ApiOperation("删除指定教师")
    @DeleteMapping("/teachers/{tid}")
    public ResultVO deleteTeacher(@PathVariable("tid") String tid) {
        // 判断uid指定的用户是否存在
        User userFromDB = Optional.ofNullable(userService.getUserById(tid))
                .orElseThrow(() -> new UnauthorizedException("用户不存在"));
        teacherService.deleteTeacher(tid);
        return ResultVO.successResultVO(Map.of());
    }

    /**
     * 删除指定学生
     */
    @ApiOperation("删除指定学生")
    @DeleteMapping("/teachers/{sid}")
    public ResultVO deleteStudent(@PathVariable("sid") String sid) {
        // 判断uid指定的用户是否存在
        User userFromDB = Optional.ofNullable(userService.getUserById(sid))
                .orElseThrow(() -> new UnauthorizedException("用户不存在"));
        studentService.deleteStudent(sid);
        return ResultVO.successResultVO(Map.of());
    }
}
