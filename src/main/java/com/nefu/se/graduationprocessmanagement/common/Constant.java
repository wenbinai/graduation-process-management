package com.nefu.se.graduationprocessmanagement.common;

public interface Constant {
    interface TaskType {
        Integer CHOOSE_MENTOR = 1;
    }

    interface Redis {
        String TEACHER_QUANTITY_KEY = "teachers";
    }

    interface Role {
        Integer STUDENT_ROLE = 1;
        Integer ADMIN_ROLE = 9;
        Integer TEACHER_ROLE = 2;
        Integer DIRECTOR_ROLE = 4;
    }

    String AUTHORIZATION = "authorization";
    String MY_SECRET_KEY = "R28K42ZEJ8LWRHU5";
    String MY_SALT = "636eac2534bcfcb0";
}
