package com.nefu.se.graduationprocessmanagement.interceptor;

import com.nefu.se.graduationprocessmanagement.component.Constant;
import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader(Constant.AUTHORIZATION);
        log.debug("auth==>" + auth);
        if (auth == null || auth.length() < 50) {
            log.debug("LoginInterceptor");
            request.setAttribute("exception", ResultVO.unAuthorizationResultVO());
            request.getRequestDispatcher("/api/exception").forward(request, response);
            return false;
        } else {
            // TODO 获取用户的个人roleId
            return true;
        }
    }
}
