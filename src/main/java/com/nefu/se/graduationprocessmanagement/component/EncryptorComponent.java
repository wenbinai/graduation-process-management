package com.nefu.se.graduationprocessmanagement.component;

import com.nefu.se.graduationprocessmanagement.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
            log.debug("解析错误");
        }
        return null;
    }

    /**
     * 将 User 对象加密成 Json 数组
     *
     * @param user
     * @return
     */
    public String userToJson(User user) {
        var resultMap = Map.of("uId", user.getId(), "rId", user.getRole());
        return encrypt(resultMap);
    }

}
