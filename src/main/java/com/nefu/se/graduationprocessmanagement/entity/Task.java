package com.nefu.se.graduationprocessmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("task")
public class Task {
    private Long id;

    private String title;

    private String description;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Short type;

    private Short status;

    private Short target;

    private Long teacherId;

    private String filename;

    private String uploadRegexName;

    private String extName;

    private LocalDateTime updateTime;
}