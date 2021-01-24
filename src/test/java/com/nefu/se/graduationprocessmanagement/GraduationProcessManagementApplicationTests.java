//package com.nefu.se.graduationprocessmanagement;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@Transactional
//@Rollback(value = false)
//class GraduationProcessManagementApplicationTests {
//    @Autowired
//    private UserMapper userMapper;
//    @Test
//    public void add_test() {
//        User user = new User();
//        user.setName("SUN");
//        userMapper.insert(user);
//    }
//
//    @Test
//    void contextLoads() {
//        User u = userMapper.selectById(1350850072954339329L);
//        u.setAddress("114");
//        userMapper.updateById(u);
//    }
//
//}
