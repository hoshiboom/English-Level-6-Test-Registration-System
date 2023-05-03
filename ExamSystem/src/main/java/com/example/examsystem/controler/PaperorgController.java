package com.example.examsystem.controler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Paperorg;
import com.example.examsystem.service.PaperorgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@Validated
@CrossOrigin
@RestController //Restful风格
public class PaperorgController {

    @Resource
    private PaperorgService paperorgService;


    //分页获得列表
    @RequestMapping(value = "/paperorg", method = RequestMethod.GET)
    public Response PaperorgPage(@RequestParam(required = false) Integer curpage,
                                @RequestParam(required = false) Integer size) {
        if (curpage == null || curpage <= 0) curpage = 1;
        if (size == null || size <= 0 || size > 10) size = 10;

        Page<Paperorg> page = paperorgService.page(new Page<>(curpage, size),
                new QueryWrapper<>());
        return new Response(ResponseEnum.List_Paperorg_Success, page);

    }

    @RequestMapping(value = "/paperorgDetail", method = RequestMethod.GET)
    public Response PaperorgDetails(@RequestParam("paperinfoId")Integer paperinfoId) {

        return paperorgService.getPaperorgDetails(paperinfoId, 1, 20);
    }

    //添加信息
    @RequestMapping(value = "/paperorg", method = RequestMethod.POST)
    public Response addPaperorg(@RequestParam("questionID") Integer questionID,
                                @RequestParam("paperinfoID") Integer paperinfoID
    ) {

        Paperorg paperorg = new Paperorg().setQuestionId(questionID).setPaperinfoId(paperinfoID);
        return paperorgService.insertPaperorg(paperorg);
    }

    //根据id删除信息
    @RequestMapping(value = "/paperorg/{id}", method = RequestMethod.DELETE)
    public Response deletePaperorg(@PathVariable("id") Integer id) {
        return paperorgService.deletePaperorg(id);
    }
}
