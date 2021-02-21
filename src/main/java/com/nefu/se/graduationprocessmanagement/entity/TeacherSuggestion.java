package com.nefu.se.graduationprocessmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("teacher_suggestion")
public class TeacherSuggestion {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Long teacherId;

    private Long studentId;

    private Long taskId;

    private String suggestion;

    private LocalDateTime updateTime;
}