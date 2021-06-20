package com.nefu.se.graduationprocessmanagement.controller;

import com.nefu.se.graduationprocessmanagement.common.Constant;
import com.nefu.se.graduationprocessmanagement.common.MyException;
import com.nefu.se.graduationprocessmanagement.dto.StudentInfoDTO;
import com.nefu.se.graduationprocessmanagement.dto.TeacherDTO;
import com.nefu.se.graduationprocessmanagement.dto.TeacherQuantity;
import com.nefu.se.graduationprocessmanagement.entity.Student;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api("系主任相关接口")
@RestController
@RequestMapping("/api/director")
@Slf4j
@Transactional
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
    public ResultVO updateAllQuantity(@RequestBody List<TeacherQuantity> teacherQuantities) {
        teacherQuantities.stream()
                .forEach((teacherQuantity) -> {
                    // TODO: 类型转换问题 优化批量更新数据
                    teacherService.updateQuantity(teacherQuantity.getId(),
                            teacherQuantity.getQuantity());
                });
        List<TeacherDTO> teachers = teacherService.listTeachers();
        log.debug("after update teachers ==>" + teachers);
        return ResultVO.success(Map.of("teachers", teachers));
    }

    @ApiOperation(("添加教师"))
    @PostMapping("teacher")
    public ResultVO addTeacher(@RequestBody TeacherDTO teacherDTO) {
        User user = new User();
        user.setNumber(teacherDTO.getNumber());
        user.setName(teacherDTO.getName());
        user.setPassword(passwordEncoder.encode(teacherDTO.getNumber()));
        user.setRole(Constant.Role.TEACHER_ROLE);
        try {
            userService.addUser(user);
        } catch (Exception e) {
            throw new MyException(200, "添加失败");
        }
        // 插入到teacher表中
        Teacher teacher = new Teacher();
        // 复用id
        teacher.setId(user.getId());
        teacher.setTitle(teacherDTO.getTitle());
        teacher.setQuantity(0);
        try {
            teacherService.addTeacher(teacher);
        } catch (Exception e) {
            throw new MyException(200, "添加失败");
        }

        List<TeacherDTO> teacherDTOs = teacherService.listTeachers();

        return ResultVO.success(Map.of("teachers", teacherDTOs));
    }


    /**
     * 导入学生功能
     *
     * @return
     */
    @ApiOperation("批量导入学生名单")
    @PostMapping("/students")
    public ResultVO initStudents(@RequestBody List<Map<String, Object>> studnets) {
        for (Map<String, Object> studnet : studnets) {
            User user = new User();
            user.setName((String) studnet.get("name"));
            user.setNumber((String) studnet.get("number"));
            user.setPassword(passwordEncoder.encode((String) studnet.get("number")));
            user.setRole(Constant.Role.STUDENT_ROLE);
            // 可能发生异常(重复数据)
            try {
                userService.insert(user);
            } catch (Exception e) {
                continue;
            }
            Student student = new Student();
            student.setId(user.getId());
            student.setClazz((String) studnet.get("clazz"));
            studentService.insert(student);
        }
        List<StudentInfoDTO> studentInfoDTOS = studentService.listStudentInfos();
        return ResultVO.success(Map.of("students", studentInfoDTOS));
    }

    /**
     * 修改指定教师的角色权限 (只能修改教师的权限为..., 不能修改学生)
     *
     * @param uid
     * @return
     */
    @ApiOperation("修改教师权限")
    @PatchMapping("/teachers/{uid}/role")
    public ResultVO updateRole(@PathVariable(value = "uid") Long uid) {
        // 判断uid指定的用户是否存在
        User userFromDB = userService.getUserById(uid);
        if (userFromDB == null) {
            throw new MyException(401, "用户不存在");
        }
        // 判断是否为教师
        log.debug("userFromDB==>", userFromDB);
        Integer role = userFromDB.getRole();
        if (role != 2) {
            return ResultVO.fail(403, "无权限");
        }
        // 修改权限为4
        userService.updateRole(uid);
        log.info("修改用户权限成功");
        // 返回角色
        return ResultVO.success(Map.of("role", 4));
    }

    /**
     * @param uid
     * @return
     */
    @ApiOperation("更新教师基本信息, 不包括重置密码")
    @PatchMapping("/teachers/{uid}/info")
    public ResultVO updateInfo(@PathVariable(value = "uid") Long uid,
                               @RequestBody Map<String, Object> map) {
        // 判断uid指定的用户是否存在
        User userFromDB = Optional.ofNullable(userService.getUserById(uid))
                .orElseThrow(() -> new UnauthorizedException("用户不存在"));
        log.debug("userFromDB: {}", userFromDB);
        log.debug("map: {}", map);
        // 更新教师的基本信息
        teacherService.updateInfo(map, uid);
        return ResultVO.success(Map.of());
    }

    /**
     * 重置密码功能
     */
    @ApiOperation("重置教师密码")
    @PutMapping("/teachers/{uid}/password")
    public ResultVO updatePassword(@PathVariable(value = "uid") Long uid) {
        // 判断uid指定的用户是否存在
        User userFromDB = Optional.ofNullable(userService.getUserById(uid))
                .orElseThrow(() -> new UnauthorizedException("用户不存在"));
        // 重置密码为number
        String newPassword = passwordEncoder.encode(userFromDB.getNumber());
        log.debug("newPassword==>" + newPassword);
        userService.updatePasswordById(uid, newPassword);
        return ResultVO.success(Map.of());
    }


    @ApiOperation("删除指定教师")
    @DeleteMapping("/teachers/{tid}")
    public ResultVO deleteTeacher(@PathVariable(value = "tid") Long tid) {
        // 判断uid指定的用户是否存在
        Optional.ofNullable(userService.getUserById(tid))
                .orElseThrow(() -> new UnauthorizedException("用户不存在"));
        teacherService.deleteTeacher(tid);
        return ResultVO.success(Map.of());
    }


    @ApiOperation("删除指定学生")
    @DeleteMapping("/students/{sid}")
    public ResultVO deleteStudent(@PathVariable(value = "sid") Long sid) {
        // 判断uid指定的用户是否存在
        Optional.ofNullable(userService.getUserById(sid))
                .orElseThrow(() -> new UnauthorizedException("用户不存在"));
        studentService.deleteStudent(sid);
        return ResultVO.success(Map.of());
    }
}
