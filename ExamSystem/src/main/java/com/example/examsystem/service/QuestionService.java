package com.example.examsystem.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.examsystem.dto.Response;
import com.example.examsystem.entity.Question;


//IService是Mybatis-Plus提供的接口，定义了基本的CRUD操作，QuestionService继承了这些操作并且可以在Question表上进行增删改查
public interface QuestionService extends IService<Question> {

    Response insertQuestion(Question question);
    Response updateQuestion(Question question);

    Response deleteQuestion(Integer id);
}
