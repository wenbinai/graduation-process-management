package com.nefu.se.graduationprocessmanagement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentInfoDTO {
    private String id;
    private String number;
    private String clazz;
    private String name;
    private String topic;
    private LocalDateTime updateTime;
}
