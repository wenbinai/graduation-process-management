package com.nefu.se.graduationprocessmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("teacher")
public class Teacher {
    private Long id;
    @TableField(value = "`group`")
    private Short group;

    private String title;

    private Short quantity;

    private String description;

    private LocalDateTime updateTime;
}