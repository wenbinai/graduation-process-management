package com.nefu.se.graduationprocessmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.se.graduationprocessmanagement.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    @Select("select * from `teacher`")
    List<Teacher> listAllTeachers();

    @Update("update `teacher` set title = #{title} where id = #{id}")
    int updateTitleById(@Param("title") String title, @Param("id") String id);

    @Update("update `teacher` set quantity = #{quantity} where id = #{id}")
    int updateQuantityById(@Param("id") String id, @Param("quantity") int quantity);
}