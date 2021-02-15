package com.nefu.se.graduationprocessmanagement.interceptor;

import com.nefu.se.graduationprocessmanagement.component.Constant;
import com.nefu.se.graduationprocessmanagement.component.EncryptorComponent;
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
public class DirectorInterceptor extends HandlerInterceptorAdapter {
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
            if (roleId == 4) {
                return true;
            } else {
                request.setAttribute("exception", ResultVO.unAuthorizationResultVO());
                request.getRequestDispatcher("/api/exception").forward(request, response);
                return false;
            }
        } catch (Exception e) {
            request.setAttribute("exception", ResultVO.unAuthorizationResultVO());
            request.getRequestDispatcher("/api/exception").forward(request, response);
            return false;
        }
    }
}
