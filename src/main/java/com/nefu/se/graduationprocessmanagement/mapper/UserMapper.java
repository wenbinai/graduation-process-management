package com.nefu.se.graduationprocessmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.se.graduationprocessmanagement.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from `user` where number = #{number}")
    User selectByNumber(String number);

    @Select("select * from `user`")
    List<User> listAllUsers();

    @Update("update `user` set role = 4 where id = #{uid}")
    int updateRoleById(String uid);

    @Update("update `user` set password = #{password} where id = #{id}")
    int updatePasswordById(@Param("id") String id, @Param("password") String password);
}