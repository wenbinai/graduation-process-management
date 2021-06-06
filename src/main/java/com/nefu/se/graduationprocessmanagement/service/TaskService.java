package com.nefu.se.graduationprocessmanagement.service;

import com.nefu.se.graduationprocessmanagement.entity.Task;
import com.nefu.se.graduationprocessmanagement.mapper.TaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TaskService {
    @Autowired
    private TaskMapper taskMapper;

    public void createScheduleTask(String startTime, String endTime) {

    }

    public void insert(Task task) {
        taskMapper.insert(task);
    }
}
