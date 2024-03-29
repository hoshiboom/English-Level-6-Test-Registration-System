package com.example.examsystem.controler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Signup;
import com.example.examsystem.mapper.SignupMapper;
import com.example.examsystem.service.SignupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.function.Function;

@Slf4j
@Validated
@CrossOrigin
@RestController //Restful风格
public class SignupController {

    @Resource
    private SignupService signupService;

    @Resource
    private SignupMapper signupMapper;

    @Resource
    @Qualifier("globalVariable")
    private Function<String, String> parameterValue;


    //分页获得列表
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public Response SignupPage(@RequestParam(required = false) Integer curpage,
                                @RequestParam(required = false) Integer size) {
        if (curpage == null || curpage <= 0) curpage = 1;
        Integer pagesize = Integer.parseInt(parameterValue.apply("Page_Size"));
        if (size == null || size <= 0 || size > pagesize) size = pagesize;

        Page<Signup> page = signupService.page(new Page<>(curpage, size),
                new QueryWrapper<Signup>());
        return new Response(ResponseEnum.List_Signup_Success, page);

    }

    //添加信息
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Response addSignup(@RequestParam("studentID") Integer studentID,
                                @RequestParam("paperinfoID") Integer paperinfoID
    ) {

        Signup signup = new Signup().setStudentId(studentID).setPaperinfoId(paperinfoID);
        return signupService.insertSignup(signup);
    }

    //根据id删除信息
    @RequestMapping(value = "/signup/{id}", method = RequestMethod.DELETE)
    public Response deleteSignup(@PathVariable("id") Integer id) {
        return signupService.deleteSignup(id);
    }
}
