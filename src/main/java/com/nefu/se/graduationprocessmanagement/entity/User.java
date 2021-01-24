package com.nefu.se.graduationprocessmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("user")
public class User {
    private String id;

    private String number;

    private String password;

    private Integer roleId;

    public User(String number, String password) {
        this.number = number;
        this.password = password;
    }

}