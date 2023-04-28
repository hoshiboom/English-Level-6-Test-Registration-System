package com.example.examsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.examsystem.entity.Admin;
import com.example.examsystem.mapper.AdminMapper;
import com.example.examsystem.service.AdminService;

//ServiceImpl是mybatis-plus的类，默认实现IService
//AdminServiceImpl类是服务实现类的一个实例，提供AdminService接口中定义的业务逻辑的实际实现，使用AdminMapper类与数据库交互并对Admin记录执行CRUD操作。
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
