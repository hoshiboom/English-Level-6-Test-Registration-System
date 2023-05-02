package com.example.examsystem.controler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Doandcheck;
import com.example.examsystem.mapper.DoandcheckMapper;
import com.example.examsystem.service.DoandcheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@Validated
@CrossOrigin
@RestController //Restful风格
public class DoandcheckController {

    @Resource
    private DoandcheckService doandcheckService;

    @Resource
    private DoandcheckMapper doandcheckMapper;


    //分页获得列表
    @RequestMapping(value = "/doandcheck", method = RequestMethod.GET)
    public Response DoandcheckPage(@RequestParam(required = false) Integer curpage,
                                   @RequestParam(required = false) Integer size) {
        if (curpage == null || curpage <= 0) curpage = 1;
        if (size == null || size <= 0 || size > 10) size = 10;

        Page<Doandcheck> page = doandcheckService.page(new Page<>(curpage, size),
                new QueryWrapper<Doandcheck>());
        return new Response(ResponseEnum.List_Doandcheck_Success, page);

    }


    //根据id获得信息
    @RequestMapping(value = "/doandcheck/{id}", method = RequestMethod.GET)
    public Response getDoandcheckById(@PathVariable("id") Integer id) {

        Doandcheck Doandcheck = doandcheckService.getOne(
                new QueryWrapper<Doandcheck>().eq("id", id)
        );
        if (Doandcheck == null) return new Response(ResponseEnum.Get_Doandcheck_Failure);
        return new Response(ResponseEnum.Get_Doandcheck_Success, Doandcheck);
    }

    //添加信息
    @RequestMapping(value = "/doandcheck", method = RequestMethod.POST)
    public Response addDoandcheck(@RequestParam("studentId") Integer studentId,
                                  @RequestParam("paperinfoId") Integer paperinfoId,
                                  @RequestParam("questionId") Integer questionId,
                                  @RequestParam("studentAnswer") String studentAnswer,
                                  @RequestParam("actualScore") Integer actualScore,
                                  @RequestParam("state") Integer state
    ) {

        Doandcheck doandcheck = new Doandcheck().setStudentId(studentId).setPaperinfoId(paperinfoId).setQuestionId(questionId)
                .setStudentAnswer(studentAnswer).setActualScore(actualScore).setState(state);
        return doandcheckService.insertDoandcheck(doandcheck);
    }

    //根据id更新信息
    @RequestMapping(value = "/doandcheck/{id}", method = RequestMethod.PUT)
    public Response updateDoandcheck(@RequestParam("studentId") Integer studentId,
                                     @RequestParam("paperinfoId") Integer paperinfoId,
                                     @RequestParam("questionId") Integer questionId,
                                     @RequestParam("studentAnswer") String studentAnswer,
                                     @RequestParam("actualScore") Integer actualScore,
                                     @RequestParam("state") Integer state,
                                     @PathVariable("id") Integer id
    ) {

        Doandcheck doandcheck = new Doandcheck().setId(id).setStudentId(studentId).setPaperinfoId(paperinfoId).setQuestionId(questionId)
                .setStudentAnswer(studentAnswer).setActualScore(actualScore).setState(state);
        return doandcheckService.updateDoandcheck(doandcheck);
    }

    //根据id删除信息
    @RequestMapping(value = "/doandcheck/{id}", method = RequestMethod.DELETE)
    public Response deleteDoandcheck(@PathVariable("id") Integer id) {
        return doandcheckService.deleteDoandcheck(id);
    }
}
