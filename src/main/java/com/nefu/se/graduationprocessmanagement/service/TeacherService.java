package com.nefu.se.graduationprocessmanagement.service;

import com.nefu.se.graduationprocessmanagement.entity.Teacher;
import com.nefu.se.graduationprocessmanagement.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;


    /**
     * 添加教师
     *
     * @param teacher
     * @return
     */
    public int addTeacher(Teacher teacher) {
        return teacherMapper.insert(teacher);
    }

    /**
     * 获取插入的所有教师
     *
     * @return
     */
    public List<Teacher> listTeachers() {
        return teacherMapper.listAllTeachers();
    }

    public int updateTitle(String title, String id) {
        return teacherMapper.updateTitleById(title, id);
    }

    public int deleteTeacher(String tid) {
        return teacherMapper.deleteById(tid);
    }

    public int updateQuantity(String id, int quantity) {
        return teacherMapper.updateQuantityById(id, quantity);
    }
}
