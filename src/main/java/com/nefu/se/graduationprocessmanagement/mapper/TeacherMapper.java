package com.nefu.se.graduationprocessmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.se.graduationprocessmanagement.dto.TeacherDTO;
import com.nefu.se.graduationprocessmanagement.dto.TeacherInfoDTO;
import com.nefu.se.graduationprocessmanagement.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    List<TeacherDTO> listAllTeachers();

    @Update("update `teacher` set title = #{title} where id = #{id}")
    int updateTitleById(@Param("title") Long title, @Param("id") String id);

    @Update("update `teacher` set quantity = #{quantity} where id = #{id}")
    int updateQuantityById(@Param("id") Long id, @Param("quantity") int quantity);


    List<TeacherInfoDTO> listTeacherInfos();
}