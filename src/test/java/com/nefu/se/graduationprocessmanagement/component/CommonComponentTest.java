package com.nefu.se.graduationprocessmanagement.component;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CommonComponentTest {
    @Autowired
    private CommonComponent commonComponent;

    @Test
    public void getRoleHex_test() {
        String roleHex = commonComponent.getRandomString();
        log.info("role ==>" + roleHex);
    }
}
