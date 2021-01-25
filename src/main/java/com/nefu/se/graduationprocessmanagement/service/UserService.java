package com.nefu.se.graduationprocessmanagement.service;

import com.nefu.se.graduationprocessmanagement.entity.User;
import com.nefu.se.graduationprocessmanagement.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User getUser(String number) {
        return userMapper.selectByNumber(number);
    }

//    public ResultVO doLogin(User user) {
//        // 从数据库中查找用户
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("number", user.getNumber());
//        User userFromDB = userMapper.selectOne(wrapper);
//        // 判断用户number是否正确
//        if (userFromDB == null) {
//            return ResultVO.unAuthorizationResultVO()
//                    .setMessage("用户不存在");
//        }
//        // 判断用户名密码是否正确
//        if (!passwordEncoder.matches(user.getPassword(), userFromDB.getPassword())) {
//            return ResultVO.unAuthorizationResultVO()
//                    .setMessage("密码不正确");
//        }
//        // 设置 header
//        setAuthorized(userFromDB);
//        // 返回生成 10 位随机字符
//        return ResultVO.successResultVO()
//                .setMessage("登陆成功")
//                .setData(Map.of("role", getRoleHex(userFromDB.getRoleId())));
//    }
//
//
//    private String getRoleHex(Integer roleId) {
//        String randomStr = UUID.randomUUID().toString().substring(0, 7);
//        randomStr = new StringBuilder(randomStr).replace(3, 3, String.valueOf(roleId)).toString();
//        log.info("randomStr ==>" + randomStr);
//        return randomStr;
//    }
//
//    /**
//     * 设置 Authorize token
//     *
//     * @param user
//     */
//    private void setAuthorized(User user) {
//        commonComponent.getCurrentResponse()
//                .setHeader(Constant.AUTHORIZATION, encryptorComponent.encrypt(resultMap));
//    }


}
