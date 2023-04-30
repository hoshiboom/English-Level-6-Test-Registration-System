package com.example.examsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Admin;
import com.example.examsystem.exception.DaoException;
import com.example.examsystem.mapper.AdminMapper;
import com.example.examsystem.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service    //bean
//ServiceImpl是mybatis-plus的类，默认实现IService
//AdminServiceImpl类是服务实现类的一个实例，提供AdminService接口中定义的业务逻辑的实际实现，使用AdminMapper类与数据库交互并对Admin记录执行CRUD操作。
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Resource
    private AdminMapper adminMapper;
    @Override
    public Admin login(Integer number, String password) {
        //调用mapper dao层功能
        return adminMapper.selectByNumberAndPassword(number, password);

    }
}
