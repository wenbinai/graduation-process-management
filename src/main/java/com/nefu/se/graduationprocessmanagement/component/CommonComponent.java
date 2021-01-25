package com.nefu.se.graduationprocessmanagement.component;

import com.nefu.se.graduationprocessmanagement.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.Map;
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

    // 单例模式, 提出, 减少数组创建次数
    private byte[] array = new byte[5];
    private SecureRandom secureRandom = new SecureRandom();
    private char[] encode;

    /**
     * 生成随机字符串
     *
     * @return
     */
    public String getRandomString() {
        secureRandom.nextBytes(array);
        encode = Hex.encode(array);
        String hex = new String(encode);
        return hex;
    }

    /**
     * 将用户信息设置到 repsonseHeader
     */
    public void setResponseHeader(String str) {
        getCurrentResponse().setHeader(Constant.AUTHORIZATION, str);
    }
}
