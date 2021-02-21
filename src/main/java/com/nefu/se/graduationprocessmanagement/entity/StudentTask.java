package com.nefu.se.graduationprocessmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("student_task")
public class StudentTask {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Long taskId;

    private String description;

    private String filename;

    private LocalDateTime updateTime;

    private Long studentId;
}