package com.nefu.se.graduationprocessmanagement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private String startTime;
    private String endTime;
}
