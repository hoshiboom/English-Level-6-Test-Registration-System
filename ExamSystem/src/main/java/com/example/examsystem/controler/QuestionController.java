package com.example.examsystem.controler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Question;
import com.example.examsystem.mapper.QuestionMapper;
import com.example.examsystem.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@Validated
@CrossOrigin
@RestController //Restful风格
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionMapper questionMapper;


    //分页获得列表
    @RequestMapping(value = "/question", method = RequestMethod.GET)
    public Response QuestionPage(@RequestParam(required = false) Integer curpage,
                                @RequestParam(required = false) Integer size) {
        if (curpage == null || curpage <= 0) curpage = 1;
        if (size == null || size <= 0 || size > 10) size = 10;

        Page<Question> page = questionService.page(new Page<>(curpage, size),
                new QueryWrapper<Question>());
        return new Response(ResponseEnum.List_Question_Success, page);

    }


    //根据id获得信息
    @RequestMapping(value = "/question/{id}", method = RequestMethod.GET)
    public Response getQuestionById(@PathVariable("id") Integer id) {

        Question Question = questionService.getOne(
                new QueryWrapper<Question>().eq("id", id)
        );
        if (Question == null) return new Response(ResponseEnum.Get_Question_Failure);
        return new Response(ResponseEnum.Get_Question_Success, Question);
    }

    @RequestMapping(value = "/questionByName", method = RequestMethod.GET)
    public Response getQuestionByName(@RequestParam("questionName") String questionName,
                                      @RequestParam(required = false) Integer curpage,
                                      @RequestParam(required = false) Integer size
    ) {
        if (curpage == null || curpage <= 0) curpage = 1;
        if (size == null || size <= 0 || size > 20) size = 20;
        Page<Question> page = questionService.page(
                new Page<>(curpage, size),
                new QueryWrapper<Question>().like("question_name", questionName + "%")
        );
        if (page == null) return new Response(ResponseEnum.Get_Question_Failure);
        return new Response(ResponseEnum.Get_Question_Success, page);
    }

    //添加信息
    @RequestMapping(value = "/question", method = RequestMethod.POST)
    public Response addQuestion(@RequestParam("questionName") String questionName,
                                @RequestParam("questionDescription") String questionDescription,
                                @RequestParam("optionA") String optionA,
                                @RequestParam("optionB") String optionB,
                                @RequestParam("optionC") String optionC,
                                @RequestParam("optionD") String optionD,
                                @RequestParam("answer") String answer,
                                @RequestParam("typeId") Integer typeId,
                                @RequestParam("score") Integer score
    ) {

        Question question = new Question().setQuestionName(questionName).setQuestionDescription(questionDescription)
                .setOptionA(optionA).setOptionB(optionB).setOptionC(optionC).setOptionD(optionD).setAnswer(answer).setTypeId(typeId).setScore(score);

        return questionService.insertQuestion(question);
    }

    //根据id更新信息
    @RequestMapping(value = "/question/{id}", method = RequestMethod.PUT)
    public Response updateQuestion(@RequestParam("questionName") String questionName,
                                   @RequestParam("questionDescription") String questionDescription,
                                   @RequestParam("optionA") String optionA,
                                   @RequestParam("optionB") String optionB,
                                   @RequestParam("optionC") String optionC,
                                   @RequestParam("optionD") String optionD,
                                   @RequestParam("answer") String answer,
                                   @RequestParam("typeId") Integer typeId,
                                   @RequestParam("score") Integer score,
                                   @PathVariable("id") Integer id
    ) {

        Question question = new Question().setId(id).setQuestionName(questionName).setQuestionDescription(questionDescription)
                .setOptionA(optionA).setOptionB(optionB).setOptionC(optionC).setOptionD(optionD).setAnswer(answer).setTypeId(typeId).setScore(score);
        return questionService.updateQuestion(question);
    }

    //根据id删除信息
    @RequestMapping(value = "/question/{id}", method = RequestMethod.DELETE)
    public Response deleteQuestion(@PathVariable("id") Integer id) {
        return questionService.deleteQuestion(id);
    }
}
