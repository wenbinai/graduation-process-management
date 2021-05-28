package com.nefu.se.graduationprocessmanagement.interceptor;

import com.nefu.se.graduationprocessmanagement.common.Constant;
import com.nefu.se.graduationprocessmanagement.common.EncryptorComponent;
import com.nefu.se.graduationprocessmanagement.common.MyException;
import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private EncryptorComponent encryptorComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader(Constant.AUTHORIZATION);
        log.info("auth: {}", auth);
        if (auth == null || auth.length() < 50) {
            throw new MyException(401, "未登录");
        } else {
            Map<String, Object> userMap = encryptorComponent.decrypt(auth);
            request.setAttribute("id", userMap.get("uId"));
            return true;
        }
    }
}
