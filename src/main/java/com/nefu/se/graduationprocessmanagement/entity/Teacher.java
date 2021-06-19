package com.nefu.se.graduationprocessmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@TableName("teacher")
public class Teacher {
    private Long id;
    @TableField(value = "`group`")
    private Short group;

    private String title;

    private Integer quantity;

    private String description;

    private LocalDateTime updateTime;

    @Version
    private Integer version;
}