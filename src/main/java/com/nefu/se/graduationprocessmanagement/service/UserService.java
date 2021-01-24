package com.nefu.se.graduationprocessmanagement.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.se.graduationprocessmanagement.component.CommonComponent;
import com.nefu.se.graduationprocessmanagement.component.Constant;
import com.nefu.se.graduationprocessmanagement.component.EncryptorComponent;
import com.nefu.se.graduationprocessmanagement.entity.User;
import com.nefu.se.graduationprocessmanagement.mapper.UserMapper;
import com.nefu.se.graduationprocessmanagement.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private EncryptorComponent encryptorComponent;

    public ResultVO doLogin(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("number", user.getNumber());
        User userFromDB = userMapper.selectOne(wrapper);
        // 判断用户number是否正确
        if (userFromDB == null) {
            return ResultVO.unAuthorizationResultVO()
                    .setMessage("用户不存在");
        }
        // 判断用户名密码是否正确
        if (!passwordEncoder.matches(user.getPassword(), userFromDB.getPassword())) {
            return ResultVO.unAuthorizationResultVO()
                    .setMessage("密码不正确");
        }
        // 设置 header
        setAuthorized(userFromDB);
        // 返回生成 8 位随机字符
        return ResultVO.successResultVO()
                .setMessage("登陆成功")
                .setData(Map.of("role", getRoleHex(userFromDB.getRoleId())));
    }


    private String getRoleHex(Integer roleId) {
        String randomStr = UUID.randomUUID().toString().substring(0, 8);
        randomStr = new StringBuilder(randomStr).replace(3, 3, String.valueOf(roleId)).toString();
        log.info("randomStr ==>" + randomStr);
        return randomStr;
    }

    /**
     * 设置 Authorize token
     *
     * @param user
     */
    private void setAuthorized(User user) {
        var resultMap = Map.of("uId", user.getId(), "rId", user.getRoleId());
        commonComponent.getCurrentResponse()
                .setHeader(Constant.AUTHORIZATION, encryptorComponent.encrypt(resultMap));
    }


}
