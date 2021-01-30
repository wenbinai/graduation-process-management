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
        return userMapper.insert(user);
    }

    public List<User> listAllUsers() {
        return userMapper.listAllUsers();
    }


}
