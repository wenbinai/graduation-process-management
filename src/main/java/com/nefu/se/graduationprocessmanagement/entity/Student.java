package com.nefu.se.graduationprocessmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@TableName("student")
public class Student {
    private Long id;

    private String clazz;

    private Long teacherId;

    @TableField(value = "`group`")
    private Integer group;

    private String topic;

    private LocalDateTime updateTime;
}