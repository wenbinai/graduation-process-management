package com.nefu.se.graduationprocessmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@TableName("task")
public class Task {
    private Long id;

    private String title;

    private String description;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer type;

    private Integer status;

    private Integer target;

    private Long teacherId;

    private String filename;

    private String uploadRegexName;

    private String extName;

    private LocalDateTime updateTime;
}