package com.example.examsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Signup;
import com.example.examsystem.exception.DaoException;
import com.example.examsystem.mapper.SignupMapper;
import com.example.examsystem.service.SignupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service    //bean
//ServiceImpl是mybatis-plus的类，默认实现IService
//SignupServiceImpl类是服务实现类的一个实例，提供SignupService接口中定义的业务逻辑的实际实现，使用SignupMapper类与数据库交互并对Signup记录执行CRUD操作。
public class SignupServiceImpl extends ServiceImpl<SignupMapper, Signup> implements SignupService {

    @Resource
    private SignupMapper signupMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response insertSignup(Signup signup){

        signupMapper.insert(signup);
        return new Response(ResponseEnum.Add_Signup_Success);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response updateSignup(Signup signup){
        Signup signuptest = this.getOne(
                new QueryWrapper<Signup>().eq("id",signup.getId())
        );
        if (signuptest == null) return new Response(ResponseEnum.Update_Signup_Failure);
        if(!this.updateById(signup)){
            throw new DaoException(ResponseEnum.Update_Signup_Failure);
        }
        return new Response(ResponseEnum.Update_Signup_Success);
    }

    @Override
    public Response deleteSignup(Integer id) {
        Signup signuptest = this.getOne(
                new QueryWrapper<Signup>().eq("id",id)
        );
        if (signuptest == null) return new Response(ResponseEnum.Delete_Signup_Failure);
        if(!this.removeById(signuptest)){
            throw new DaoException(ResponseEnum.Delete_Signup_Failure);
        }
        return new Response(ResponseEnum.Delete_Signup_Success);
    }
}
