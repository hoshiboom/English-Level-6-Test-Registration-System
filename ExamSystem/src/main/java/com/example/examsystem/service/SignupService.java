package com.example.examsystem.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.examsystem.dto.Response;
import com.example.examsystem.entity.Signup;


//IService是Mybatis-Plus提供的接口，定义了基本的CRUD操作，SignupService继承了这些操作并且可以在Signup表上进行增删改查
public interface SignupService extends IService<Signup> {

    Response insertSignup(Signup signup);
    Response updateSignup(Signup signup);

    Response deleteSignup(Integer id);
}
