package com.example.examsystem.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.examsystem.dto.Response;
import com.example.examsystem.entity.Doandcheck;


//IService是Mybatis-Plus提供的接口，定义了基本的CRUD操作，DoandcheckService继承了这些操作并且可以在Doandcheck表上进行增删改查
public interface DoandcheckService extends IService<Doandcheck> {

    Response insertDoandcheck(Doandcheck Doandcheck);
    Response updateDoandcheck(Doandcheck Doandcheck);

    Response deleteDoandcheck(Integer id);
}
