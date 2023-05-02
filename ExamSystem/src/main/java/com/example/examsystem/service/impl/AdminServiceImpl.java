package com.example.examsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Admin;
import com.example.examsystem.exception.DaoException;
import com.example.examsystem.mapper.AdminMapper;
import com.example.examsystem.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.SystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response insertAdmin(Admin admin){
        if(admin.getNumber()!=null) {
            Admin admin1 = this.getOne(new QueryWrapper<Admin>().eq("number", admin.getNumber()));
            if (admin1!= null) {
                return new Response(ResponseEnum.Add_Admin_Failure);
            }
        }
        adminMapper.insert(admin);
        return new Response(ResponseEnum.Add_Admin_Success);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response updateAdmin(Admin admin){
        Admin admintest = this.getOne(
                new QueryWrapper<Admin>().eq("id",admin.getId())
        );
        if (admintest == null) return new Response(ResponseEnum.Update_Admin_Failure);
        if(!this.updateById(admin)){
            throw new DaoException(ResponseEnum.Update_Admin_Failure);
        }
        return new Response(ResponseEnum.Update_Admin_Success);
    }

    @Override
    public Response deleteAdmin(Integer id) {
        Admin admintest = this.getOne(
                new QueryWrapper<Admin>().eq("id",id)
        );
        if (admintest == null) return new Response(ResponseEnum.Delete_Admin_Failure);
        if(!this.removeById(admintest)){
            throw new DaoException(ResponseEnum.Delete_Admin_Failure);
        }
        return new Response(ResponseEnum.Delete_Admin_Success);
    }
}
