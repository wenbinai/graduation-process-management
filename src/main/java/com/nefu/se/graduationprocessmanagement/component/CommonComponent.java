package com.nefu.se.graduationprocessmanagement.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
@Slf4j
public class CommonComponent {
    /**
     * 获取当前请求
     *
     * @return
     */
    public HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
    }

    /**
     * 获取当前的响应
     *
     * @return
     */
    public HttpServletResponse getCurrentResponse() {
        return ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getResponse();
    }
}
