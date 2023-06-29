package com.example.examsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Doandcheck;
import com.example.examsystem.entity.Paperorg;
import com.example.examsystem.entity.Question;
import com.example.examsystem.exception.DaoException;
import com.example.examsystem.mapper.DoandcheckMapper;
import com.example.examsystem.service.DoandcheckService;
import com.example.examsystem.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service    //bean
//ServiceImpl是mybatis-plus的类，默认实现IService
//DoandcheckServiceImpl类是服务实现类的一个实例，提供DoandcheckService接口中定义的业务逻辑的实际实现，使用DoandcheckMapper类与数据库交互并对Doandcheck记录执行CRUD操作。
public class DoandcheckServiceImpl extends ServiceImpl<DoandcheckMapper, Doandcheck> implements DoandcheckService {

    @Resource
    private DoandcheckMapper doandcheckMapper;

    @Resource
    private QuestionService questionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response insertDoandcheck(Doandcheck doandcheck){
        Question question = questionService.getOne(new QueryWrapper<Question>().eq("id",doandcheck.getQuestionId()));
        if(question==null){
            throw new DaoException(ResponseEnum.TEST_TEST);
        }
        if(question.getTypeId()==1||question.getTypeId()==2){
            if(question.getAnswer().equals(doandcheck.getStudentAnswer())){
                doandcheck.setActualScore(question.getScore());
            }
            else{
                doandcheck.setActualScore(0);
            }
            doandcheck.setState(2);
        }
        doandcheckMapper.insert(doandcheck);
        return new Response(ResponseEnum.Add_Doandcheck_Success,doandcheck);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response updateDoandcheck(Doandcheck doandcheck){
        Doandcheck doandchecktest = this.getOne(
                new QueryWrapper<Doandcheck>().eq("id",doandcheck.getId())
        );
        if (doandchecktest == null) return new Response(ResponseEnum.Update_Doandcheck_Failure);
        if(!this.updateById(doandcheck)){
            throw new DaoException(ResponseEnum.Update_Doandcheck_Failure);
        }
        return new Response(ResponseEnum.Update_Doandcheck_Success);
    }

    @Override
    public Response deleteDoandcheck(Integer id) {
        Doandcheck doandchecktest = this.getOne(
                new QueryWrapper<Doandcheck>().eq("id",id)
        );
        if (doandchecktest == null) return new Response(ResponseEnum.Delete_Doandcheck_Failure);
        if(!this.removeById(doandchecktest)){
            throw new DaoException(ResponseEnum.Delete_Doandcheck_Failure);
        }
        return new Response(ResponseEnum.Delete_Doandcheck_Success);
    }

    @Override
    public Response AutogradingAll() {



        return new Response(ResponseEnum.AutoGrading_Doandcheck_Success);

    }

    @Override
    public Response getCheck(Integer type, Integer state) {
        //Page<Question> questionPage = new Page<Question>(1,1000);
        Page<Doandcheck> tmpPage = new Page<Doandcheck>(1,1000);
        Page<Doandcheck> doandcheckPage = doandcheckMapper.getCheck(type, state,tmpPage);
        if(doandcheckPage == null) return new Response(ResponseEnum.Get_Doandcheck_Failure);
        return new Response(ResponseEnum.Get_Doandcheck_Success,doandcheckPage);

    }
}
