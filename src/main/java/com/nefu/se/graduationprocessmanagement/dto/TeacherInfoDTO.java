package com.nefu.se.graduationprocessmanagement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TeacherInfoDTO {
    private Long id;
    private String title;
    private String name;
    private String description;
    private Short group;
    private Integer quantity;
    private LocalDateTime updateTime;
}
