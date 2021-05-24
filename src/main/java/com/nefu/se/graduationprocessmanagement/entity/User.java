package com.nefu.se.graduationprocessmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@TableName("user")
public class User {
    private Long id;

    private String number;

    private String name;

    private Integer role;

    private LocalDateTime updateTime;

    private String password;
}