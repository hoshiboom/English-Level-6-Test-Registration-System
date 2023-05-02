package com.example.examsystem.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.examsystem.dto.Response;
import com.example.examsystem.entity.Teacher;


//IService是Mybatis-Plus提供的接口，定义了基本的CRUD操作，TeacherService继承了这些操作并且可以在Teacher表上进行增删改查
public interface TeacherService extends IService<Teacher> {
    Teacher login(Integer number, String password);
    Response insertTeacher(Teacher Teacher);
    Response updateTeacher(Teacher Teacher);

    Response deleteTeacher(Integer id);
}
