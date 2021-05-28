package com.nefu.se.graduationprocessmanagement.service;

import com.nefu.se.graduationprocessmanagement.dto.TeacherDTO;
import com.nefu.se.graduationprocessmanagement.dto.TeacherInfoDTO;
import com.nefu.se.graduationprocessmanagement.entity.Teacher;
import com.nefu.se.graduationprocessmanagement.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    public void updateInfo(Map<String, Object> map, long id) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        if (map.get("title") != null)
            teacher.setTitle((String) map.get("title"));
        if (map.get("description") != null)
            teacher.setDescription((String) map.get("description"));
        if (map.get("group") != null)
            teacher.setGroup((Short) map.get("group"));
        teacherMapper.updateById(teacher);
    }

    /**
     * 获取插入的所有教师
     *
     * @return
     */
    public List<TeacherDTO> listTeachers() {
        return teacherMapper.listAllTeachers();
    }


    public int deleteTeacher(Long tid) {
        return teacherMapper.deleteById(tid);
    }

    public int updateQuantity(Long id, int quantity) {
        return teacherMapper.updateQuantityById(id, quantity);
    }

    public List<TeacherInfoDTO> listTeacherInfos() {
        return teacherMapper.listTeacherInfos();
    }
}
