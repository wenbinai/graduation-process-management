package com.nefu.se.graduationprocessmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("student")
public class Student {
    private Long id;

    private String clazz;

    private Long teacherId;

    private Short group;

    private String topic;

    private LocalDateTime updateTime;
}