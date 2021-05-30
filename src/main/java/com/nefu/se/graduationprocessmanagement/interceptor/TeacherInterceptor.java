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
public class TeacherInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private EncryptorComponent encryptorComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader(Constant.AUTHORIZATION);
        log.debug("header ==>" + header);
        try {
            Map<String, Object> map = encryptorComponent.decrypt(header);
            log.debug("map ==>" + map);
            Integer roleId = (Integer) map.get("rId");
            log.debug("roleId==>" + roleId);
            if (roleId >= Constant.Role.TEACHER_ROLE) {
                return true;
            } else {
                throw new MyException(403, "权限不足");
            }
        } catch (Exception e) {
            throw new MyException(403, "权限不足");
        }
    }
}
