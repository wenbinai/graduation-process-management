package com.nefu.se.graduationprocessmanagement.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nefu.se.graduationprocessmanagement.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.encrypt.Encryptors;

import java.util.Map;

@Component
@Slf4j
public class EncryptorComponent {
    //    @Value("${my.secretkey}")
    private String secretKey = Constant.MY_SECRET_KEY;
    //    @Value("${salt}")
    private String salt = Constant.MY_SALT;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TextEncryptor encryptor;

    /**
     * 直接基于密钥/盐值创建单例对象, 避免反复创建
     *
     * @return
     */
    @Bean
    public TextEncryptor getTextEncryptor() {
        return Encryptors.text(secretKey, salt);
    }

    public String encrypt(Map<String, Object> payload) {
        try {
            String json = objectMapper.writeValueAsString(payload);
            return encryptor.encrypt(json);
        } catch (JsonProcessingException e) {
            throw new MyException(500, "服务端错误");
        }
    }

    public Map<String, Object> decrypt(String auth) {
        try {
            String json = encryptor.decrypt(auth);
            return objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            throw new MyException(403, "无权限");
        }
    }


    /**
     * 将 User 对象加密成 Json 数组
     *
     * @param user
     * @return
     */
    public String userToJson(User user) {
        Map<String, Object> resultMap = Map.of("uId", user.getId(), "rId", user.getRole());
        return encrypt(resultMap);
    }
}
