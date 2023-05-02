package com.example.examsystem.controler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Teacher;
import com.example.examsystem.mapper.TeacherMapper;
import com.example.examsystem.service.TeacherService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@Validated
@CrossOrigin
@RestController //Restful风格
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @Resource
    private TeacherMapper teacherMapper;


    //分页获得列表
    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public Response TeacherPage(@RequestParam(required = false) Integer curpage,
                                @RequestParam(required = false) Integer size) {
        if (curpage == null || curpage <= 0) curpage = 1;
        if (size == null || size <= 0 || size > 10) size = 10;

        Page<Teacher> page = teacherService.page(new Page<>(curpage, size),
                new QueryWrapper<Teacher>().select("id", "name", "password", "number","school", "email","phone"));
        return new Response(ResponseEnum.List_Teacher_Success, page);

    }


    //根据id获得用户信息
    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET)
    public Response getTeacherById(@PathVariable("id") Integer id) {

        Teacher Teacher = teacherService.getOne(
                new QueryWrapper<Teacher>().eq("id", id)
        );
        if (Teacher == null) return new Response(ResponseEnum.Get_Teacher_Failure);
        return new Response(ResponseEnum.Get_Teacher_Success, Teacher);
    }

    //添加用户信息
    @RequestMapping(value = "/teacher", method = RequestMethod.POST)
    public Response addTeacher(@NonNull @RequestParam("name") String name,
                               @NonNull @RequestParam("password") String password,
                               @RequestParam("number") Integer number,
                               @RequestParam("phone") String phone,
                               @RequestParam("school") String school,
                               @RequestParam("email") String email) {
        Teacher Teacher = new Teacher().setName(name).setPassword(password).setEmail(email).setNumber(number).setPhone(phone).setSchool(school);


        return teacherService.insertTeacher(Teacher);
    }

    //根据id更新用户信息
    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.PUT)
    public Response updateTeacher(@PathVariable("id") Integer id,
                                  @NonNull @RequestParam("name") String name,
                                  @NonNull @RequestParam("password") String password,
                                  @RequestParam("number") Integer number,
                                  @RequestParam("phone") String phone,
                                  @RequestParam("school") String school,
                                  @RequestParam("email") String email) {

        Teacher Teacher = new Teacher().setId(id).setName(name).setPassword(password).setEmail(email).setNumber(number).setPhone(phone).setSchool(school);

        return teacherService.updateTeacher(Teacher);
    }

    //根据id删除用户信息
    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.DELETE)
    public Response deleteTeacher(@PathVariable("id") Integer id) {
        return teacherService.deleteTeacher(id);
    }
}
