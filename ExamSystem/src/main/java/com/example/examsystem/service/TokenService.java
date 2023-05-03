package com.example.examsystem.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.examsystem.dto.Response;
import com.example.examsystem.entity.Token;


//IService是Mybatis-Plus提供的接口，定义了基本的CRUD操作，TokenService继承了这些操作并且可以在Token表上进行增删改查
public interface TokenService extends IService<Token> {
    boolean insertToken(Token Token);

    boolean deleteToken(Integer id);
}
