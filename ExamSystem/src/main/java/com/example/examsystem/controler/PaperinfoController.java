package com.example.examsystem.controler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Paperinfo;
import com.example.examsystem.mapper.PaperinfoMapper;
import com.example.examsystem.service.PaperinfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@Validated
@CrossOrigin
@RestController //Restful风格
public class PaperinfoController {

    @Resource
    private PaperinfoService paperinfoService;

    @Resource
    private PaperinfoMapper paperinfoMapper;


    //分页获得列表
    @RequestMapping(value = "/paperinfo", method = RequestMethod.GET)
    public Response PaperinfoPage(@RequestParam(required = false) Integer curpage,
                                @RequestParam(required = false) Integer size) {
        if (curpage == null || curpage <= 0) curpage = 1;
        if (size == null || size <= 0 || size > 10) size = 10;

        Page<Paperinfo> page = paperinfoService.page(new Page<>(curpage, size),
                new QueryWrapper<Paperinfo>());
        return new Response(ResponseEnum.List_Paperinfo_Success, page);

    }


    //根据id获得信息
    @RequestMapping(value = "/paperinfo/{id}", method = RequestMethod.GET)
    public Response getPaperinfoById(@PathVariable("id") Integer id) {

        Paperinfo Paperinfo = paperinfoService.getOne(
                new QueryWrapper<Paperinfo>().eq("id", id)
        );
        if (Paperinfo == null) return new Response(ResponseEnum.Get_Paperinfo_Failure);
        return new Response(ResponseEnum.Get_Paperinfo_Success, Paperinfo);
    }

    //添加信息
    @RequestMapping(value = "/paperinfo", method = RequestMethod.POST)
    public Response addPaperinfo(@RequestParam("money") String money,
                                @RequestParam("title") String title,
                                @RequestParam("description") String description,
                                @RequestParam("time") String time
    ) {

        Paperinfo paperinfo = new Paperinfo().setMoney(money).setTitle(title).setDescription(description).setTime(time);
        return paperinfoService.insertPaperinfo(paperinfo);
    }

    //根据id更新信息
    @RequestMapping(value = "/paperinfo/{id}", method = RequestMethod.PUT)
    public Response updatePaperinfo(@RequestParam("money") String money,
                                    @RequestParam("title") String title,
                                    @RequestParam("description") String description,
                                    @RequestParam("time") String time,
                                    @PathVariable("id") Integer id
    ) {

        Paperinfo paperinfo = new Paperinfo().setId(id).setMoney(money).setTitle(title).setDescription(description).setTime(time);
        return paperinfoService.updatePaperinfo(paperinfo);
    }

    //根据id删除信息
    @RequestMapping(value = "/paperinfo/{id}", method = RequestMethod.DELETE)
    public Response deletePaperinfo(@PathVariable("id") Integer id) {
        return paperinfoService.deletePaperinfo(id);
    }
}
