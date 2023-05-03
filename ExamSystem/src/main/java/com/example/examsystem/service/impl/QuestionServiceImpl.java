package com.example.examsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Paperorg;
import com.example.examsystem.entity.Question;
import com.example.examsystem.exception.DaoException;
import com.example.examsystem.mapper.PaperorgMapper;
import com.example.examsystem.mapper.QuestionMapper;
import com.example.examsystem.service.PaperorgService;
import com.example.examsystem.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service    //bean
//ServiceImpl是mybatis-plus的类，默认实现IService
//QuestionServiceImpl类是服务实现类的一个实例，提供QuestionService接口中定义的业务逻辑的实际实现，使用QuestionMapper类与数据库交互并对Question记录执行CRUD操作。
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private PaperorgMapper paperorgMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response insertQuestion(Question question){

        questionMapper.insert(question);
        return new Response(ResponseEnum.Add_Question_Success);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response updateQuestion(Question question){
        Question questiontest = this.getOne(
                new QueryWrapper<Question>().eq("id",question.getId())
        );
        if (questiontest == null) return new Response(ResponseEnum.Update_Question_Failure);
        if(!this.updateById(question)){
            throw new DaoException(ResponseEnum.Update_Question_Failure);
        }
        return new Response(ResponseEnum.Update_Question_Success);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response deleteQuestion(Integer id) {
        Question questiontest = this.getOne(
                new QueryWrapper<Question>().eq("id",id)
        );
        //如果此题已经被组织进试卷则不可删除
        Paperorg paperorg=paperorgMapper.selectOne(new QueryWrapper<Paperorg>().eq("paperinfo_id",id));

        if (questiontest == null || paperorg != null) return new Response(ResponseEnum.Delete_Question_Failure);
        if(!this.removeById(questiontest)){
            throw new DaoException(ResponseEnum.Delete_Question_Failure);
        }
        return new Response(ResponseEnum.Delete_Question_Success);
    }
}
