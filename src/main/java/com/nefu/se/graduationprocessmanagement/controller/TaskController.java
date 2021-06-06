package com.nefu.se.graduationprocessmanagement.controller;

import com.nefu.se.graduationprocessmanagement.dto.TaskDTO;
import com.nefu.se.graduationprocessmanagement.dto.TeacherDTO;
import com.nefu.se.graduationprocessmanagement.entity.Student;
import com.nefu.se.graduationprocessmanagement.entity.Task;
import com.nefu.se.graduationprocessmanagement.service.StudentService;
import com.nefu.se.graduationprocessmanagement.service.TaskService;
import com.nefu.se.graduationprocessmanagement.service.TeacherService;
import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/director/tasks")
@Api("任务接口")
@Slf4j
public class TaskController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TaskService taskService;

    @ApiOperation("创建选择导师任务")
    @PostMapping("choice")
    public ResultVO createTask(@RequestBody TaskDTO taskDTO) {
        // 获取所有老师所带的学生数目
        List<TeacherDTO> teacherDTOS = teacherService.listTeachers();
        Integer tCount = teacherDTOS.stream()
                .mapToInt(TeacherDTO::getQuantity)
                .sum();
        log.debug("tCount: {}", tCount);
        // 获取所有学生数目
        Integer sCount = studentService.getCount();
        log.debug("sCount: {}", sCount);
        if (!tCount.equals(sCount)) {
            return ResultVO.fail(400, "创建任务失败");
        }
        // 相同, 创建task
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStartTime(LocalDateTime.parse(taskDTO.getStartTime()));
        task.setEndTime(LocalDateTime.parse(taskDTO.getEndTime()));
        // 插入数据库中
        taskService.insert(task);
        // todo 创建一个定时任务
        taskService.createScheduleTask(taskDTO.getStartTime(), taskDTO.getEndTime());
        log.debug("创建{}任务成功", task.getTitle());
        return ResultVO.success(Map.of());
    }

    // 前端创建任务 --> 后端开始创建定时任务 --> (对应任务的api 在对应时间内开放) --> 通知前端


    // 执行对应任务api的时候, 判断时间是否在范围,
}
