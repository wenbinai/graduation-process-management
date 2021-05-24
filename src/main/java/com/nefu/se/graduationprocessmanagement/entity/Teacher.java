package com.nefu.se.graduationprocessmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@TableName("teacher")
public class Teacher {
    private Long id;

    private Short group;

    private String title;

    private Integer quantity;

    private String description;

    private LocalDateTime updateTime;
}