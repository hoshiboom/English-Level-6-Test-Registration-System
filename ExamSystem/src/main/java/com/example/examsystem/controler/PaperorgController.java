package com.example.examsystem.controler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.PaperView;
import com.example.examsystem.entity.Paperorg;
import com.example.examsystem.entity.Question;
import com.example.examsystem.mapper.PaperorgMapper;
import com.example.examsystem.service.PaperorgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.function.Function;

@Slf4j
@Validated
@CrossOrigin
@RestController
public class PaperorgController {
    @Resource
    private PaperorgMapper paperorgMapper;

    @Resource
    private PaperorgService paperorgService;
    @Resource
    @Qualifier("globalVariable")
    private Function<String, String> parameterValue;

    @RequestMapping(value = "/paperorg", method = RequestMethod.GET)
    public Response PaperorgPage(@RequestParam(required = false) Integer curpage,
                                 @RequestParam(required = false) Integer size) {
        if (curpage == null || curpage <= 0) curpage = 1;
        Integer pagesize = Integer.parseInt(parameterValue.apply("Page_Size"));
        if (size == null || size <= 0 || size > pagesize) size = pagesize;

        Page<Paperorg> page = paperorgService.page(new Page<>(curpage, size),
                new QueryWrapper<Paperorg>());
        return new Response(ResponseEnum.List_Paperorg_Success, page);
    }

    @RequestMapping(value = "/paperorgDetail", method = RequestMethod.GET)
    public Response PaperorgDetail(@RequestParam(required = true,value="paperinfoId") Integer paperinfoId,
                                   @RequestParam(required = false,value="curpage") Integer curpage,
                                   @RequestParam(required = false,value="size") Integer size) {
        if (curpage == null || curpage <= 0) curpage = 1;
        Integer pagesize = Integer.parseInt(parameterValue.apply("Page_Size"));
        if (size == null || size <= 0 || size > pagesize) size = pagesize;
        Page<PaperView> ipage= new Page<>(curpage,size);

        return paperorgService.getPaperorgById(paperinfoId,ipage);

    }

    @RequestMapping(value = "/paperorg" , method = RequestMethod.POST)
    public Response addQuestion(@RequestParam(required = true,value = "questionId")Integer questionId,
                                @RequestParam(required = true,value = "paperinfoId")Integer paperinfoId,
                                @RequestParam(required = true,value = "orderId")Integer orderId){
        Paperorg paperorg = new Paperorg().setQuestionId(questionId).setPaperinfoId(paperinfoId).setOrderId(orderId);
        return paperorgService.insertPaperinfo(paperorg);
    }

    @RequestMapping(value = "/paperorg/{id}" , method = RequestMethod.DELETE)
    public Response deleteQuestion(@PathVariable("id") Integer id) {

        return paperorgService.deletePaperinfo(id);
    }



}
