package com.nefu.se.graduationprocessmanagement.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class UnauthorizedException extends ResponseStatusException {
    public static final String LOGIN_ERROR = "用户名密码错误";
    public static final String REASON = "授权错误";

    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, REASON);
    }

    public UnauthorizedException(String reason) {
        super(HttpStatus.UNAUTHORIZED, reason);
    }
}
