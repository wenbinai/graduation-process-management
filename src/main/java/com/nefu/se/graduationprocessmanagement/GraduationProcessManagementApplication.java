package com.nefu.se.graduationprocessmanagement;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GraduationProcessManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduationProcessManagementApplication.class, args);
    }
}
