package com.example.examsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Token;
import com.example.examsystem.exception.DaoException;
import com.example.examsystem.mapper.TokenMapper;
import com.example.examsystem.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service    //bean
//ServiceImpl是mybatis-plus的类，默认实现IService
//TokenServiceImpl类是服务实现类的一个实例，提供TokenService接口中定义的业务逻辑的实际实现，使用TokenMapper类与数据库交互并对Token记录执行CRUD操作。
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {

    @Resource
    private TokenMapper tokenMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertToken(Token token){

        tokenMapper.insert(token);
        return true;
    }

    @Override
    public boolean deleteToken(Integer id) {
        Token Tokentest = this.getOne(
                new QueryWrapper<Token>().eq("id",id)
        );
        if (Tokentest == null) return false;
        this.removeById(Tokentest);
        return true;
    }
}
