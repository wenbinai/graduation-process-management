package com.nefu.se.graduationprocessmanagement.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private ObjectMapperComponent objectMapperComponent;

    public String encrypt(Map payload) {
        String json = objectMapperComponent.writeValueAsString(payload);
        return Encryptors.text(secretKey, salt).encrypt(json);
    }

    public Map<String, Object> decrypt(String encryptString) {
        try {
            String json = Encryptors.text(secretKey, salt).decrypt(encryptString);
            return objectMapperComponent.readValue(json);
        } catch (Exception e) {
            // TODO 标记哪一个类, 方法
            log.info("解析错误");
        }
        return null;
    }

}
