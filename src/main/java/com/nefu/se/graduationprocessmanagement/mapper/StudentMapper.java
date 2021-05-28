package com.nefu.se.graduationprocessmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.se.graduationprocessmanagement.dto.StudentInfoDTO;
import com.nefu.se.graduationprocessmanagement.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    @Select("select count(*) from student")
    Integer count();

    List<StudentInfoDTO> listStudentInfos();
}