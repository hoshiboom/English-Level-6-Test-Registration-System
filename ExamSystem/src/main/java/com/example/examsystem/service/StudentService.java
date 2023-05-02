package com.example.examsystem.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.examsystem.dto.Response;
import com.example.examsystem.entity.Student;


//IService是Mybatis-Plus提供的接口，定义了基本的CRUD操作，StudentService继承了这些操作并且可以在Student表上进行增删改查
public interface StudentService extends IService<Student> {
    Student login(Integer number, String password);
    Response insertStudent(Student Student);
    Response updateStudent(Student Student);

    Response deleteStudent(Integer id);
}
