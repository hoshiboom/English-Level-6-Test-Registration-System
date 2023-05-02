package com.example.examsystem.controler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Student;
import com.example.examsystem.mapper.StudentMapper;
import com.example.examsystem.service.StudentService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@Validated
@CrossOrigin
@RestController //Restful风格
public class StudentController {

    @Resource
    private StudentService studentService;

    @Resource
    private StudentMapper studentMapper;


    //分页获得列表
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public Response StudentPage(@RequestParam(required = false) Integer curpage,
                                @RequestParam(required = false) Integer size) {
        if (curpage == null || curpage <= 0) curpage = 1;
        if (size == null || size <= 0 || size > 10) size = 10;

        Page<Student> page = studentService.page(new Page<>(curpage, size), new QueryWrapper<Student>().select("id", "name", "password", "number","id_number", "email","phone"));
        return new Response(ResponseEnum.List_Student_Success, page);

    }


    //根据id获得用户信息
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public Response getStudentById(@PathVariable("id") Integer id) {

        Student Student = studentService.getOne(
                new QueryWrapper<Student>().eq("id", id)
        );
        if (Student == null) return new Response(ResponseEnum.Get_Student_Failure);
        return new Response(ResponseEnum.Get_Student_Success, Student);
    }

    //添加用户信息
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public Response addStudent(@NonNull @RequestParam("name") String name,
                               @NonNull @RequestParam("password") String password,
                               @RequestParam("number") Integer number,
                               @RequestParam("phone") String phone,
                               @RequestParam("idnumber") Integer idnumber,
                               @RequestParam("email") String email) {
        Student Student = new Student().setName(name).setPassword(password).setEmail(email).setNumber(number).setPhone(phone).setIdNumber(idnumber);


        return studentService.insertStudent(Student);
    }

    //根据id更新用户信息
    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public Response updateStudent(@PathVariable("id") Integer id,
                                  @NonNull @RequestParam("name") String name,
                                  @NonNull @RequestParam("password") String password,
                                  @RequestParam("number") Integer number,
                                  @RequestParam("phone") String phone,
                                  @RequestParam("idnumber") Integer idnumber,
                                  @RequestParam("email") String email) {

        Student Student = new Student().setId(id).setName(name).setPassword(password).setEmail(email).setNumber(number).setPhone(phone).setIdNumber(idnumber);

        return studentService.updateStudent(Student);
    }

    //根据id删除用户信息
    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public Response deleteStudent(@PathVariable("id") Integer id) {
        return studentService.deleteStudent(id);
    }
}
