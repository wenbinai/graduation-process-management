package com.nefu.se.graduationprocessmanagement.common;

public interface Constant {
    interface Role {
        Integer STUDENT_ROLE = 1;
        Integer ADMIN_ROLE = 8;
        Integer TEACHER_ROLE = 2;
        Integer DIRECTOR_ROLE = 4;
    }

    String AUTHORIZATION = "authorization";
    String MY_SECRET_KEY = "R28K42ZEJ8LWRHU5";
    String MY_SALT = "636eac2534bcfcb0";
}
