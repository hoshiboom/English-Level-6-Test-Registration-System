package com.example.examsystem.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.examsystem.entity.Admin;


//IService是Mybatis-Plus提供的接口，定义了基本的CRUD操作，AdminService继承了这些操作并且可以在Admin表上进行增删改查
public interface AdminService extends IService<Admin> {

}
