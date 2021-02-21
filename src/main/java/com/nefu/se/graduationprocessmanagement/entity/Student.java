package com.nefu.se.graduationprocessmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("student")
public class Student {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String clazz;

    private Long teacherId;

    private Short group;

    private String topic;

    private LocalDateTime updateTime;
}