package com.nefu.se.graduationprocessmanagement.config;

import com.nefu.se.graduationprocessmanagement.interceptor.DirectorInterceptor;
import com.nefu.se.graduationprocessmanagement.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private DirectorInterceptor directorInterceptor;

    /**
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 增加登陆拦截器
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/api/exception");
        // 增加主任拦截器
        registry.addInterceptor(directorInterceptor)
                .addPathPatterns("/api/director/**");
    }
}
