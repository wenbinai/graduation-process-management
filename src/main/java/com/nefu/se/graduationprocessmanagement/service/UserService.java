package com.nefu.se.graduationprocessmanagement.service;

import com.nefu.se.graduationprocessmanagement.entity.User;
import com.nefu.se.graduationprocessmanagement.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User getUser(String number) {
        return userMapper.selectByNumber(number);
    }

    public int addUser(User user) {
        int res = 0;
        try {
            res = userMapper.insert(user);
        } catch (Exception e) {
            throw e;
        }
        return res;
    }

    public List<User> listAllUsers() {
        return userMapper.listAllUsers();
    }


    public int updateRole(Long uid) {
        return userMapper.updateRoleById(uid);
    }

    public User getUserById(Long uid) {
        return userMapper.selectById(uid);
    }

    public int updatePasswordById(Long uid, String password) {
        return userMapper.updatePasswordById(uid, password);
    }

    public void insert(User user) {
        try {
            userMapper.insert(user);
        } catch (Exception e) {
            throw e;
        }
    }
}
