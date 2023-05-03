package com.example.examsystem.controler;

import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Admin;
import com.example.examsystem.entity.Student;
import com.example.examsystem.entity.Teacher;
import com.example.examsystem.entity.Token;
import com.example.examsystem.mapper.AdminMapper;
import com.example.examsystem.service.AdminService;
import com.example.examsystem.service.StudentService;
import com.example.examsystem.service.TeacherService;
import com.example.examsystem.service.TokenService;
import com.example.examsystem.utils.JwtUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Validated
@CrossOrigin
@RestController
public class LoginController {
    @Resource
    private AdminService adminService;
    @Resource
    private StudentService studentService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private TokenService tokensService;

    @ResponseBody   //加不加无所谓，这个注解指定将控制器返回的方法序列化成json格式的相应内容，否则spring回将该对象视为一个模型对象并传递给试图
                    //但是由于类开头用了RestController注解来定义一个控制类，该类默认将所有处理方法的返回值都视为HTTP响应体中的数据而不是视图名称
                    //替代@Controller和@ResponseBody注解可以更方便地创建RESTful风格的控制器；如果需要返回视图模板应该使用@controller注解
    @PostMapping("/login/admin")  //admin，调用admin业务层service即可
    public Response adminLogin(@NonNull @RequestParam("number")Integer number,
                          @NonNull @RequestParam("password")String password) {

        Admin a = adminService.login(number, password);
        if (a != null) {
            Map<String , Object> claims = new HashMap<>();
            claims.put("id", a.getId());
            claims.put("roleId", 1);
            claims.put("name",a.getName());
            String jwtToken = JwtUtils.generateToken(claims);
            tokensService.insertToken(new Token(jwtToken,1,a.getNumber()));
            return new Response(ResponseEnum.Login_Success,jwtToken);
        }
        else{
            return new Response(ResponseEnum.Login_Failure);
        }
    }

    @PostMapping("/login/teacher")  //admin，调用admin业务层service即可
    public Response teacherLogin(@NonNull @RequestParam("number")Integer number,
                               @NonNull @RequestParam("password")String password) {

        Teacher a = teacherService.login(number, password);
        if (a != null) {
            Map<String , Object> claims = new HashMap<>();
            claims.put("id", a.getId());
            claims.put("roleId", 2);
            claims.put("name",a.getName());
            String jwtToken = JwtUtils.generateToken(claims);
            tokensService.insertToken(new Token(jwtToken,2,a.getNumber()));
            return new Response(ResponseEnum.Login_Success,jwtToken);
        }
        else{
            return new Response(ResponseEnum.Login_Failure);
        }
    }

    @PostMapping("/login/student")  //user，调用user业务层service即可
    public Response studentLogin(@NonNull @RequestParam("number")Integer number,
                          @NonNull @RequestParam("password")String password) {

        Student a=studentService.login(number, password);
        if (a != null) {
            Map<String , Object> claims = new HashMap<>();
            claims.put("id", a.getId());
            claims.put("roleId", 3);
            claims.put("name",a.getName());
            String jwtToken = JwtUtils.generateToken(claims);
            tokensService.insertToken(new Token(jwtToken,3,a.getNumber()));
            return new Response(ResponseEnum.Login_Success,jwtToken);
        }
        else{
            return new Response(ResponseEnum.Login_Failure);
        }
    }

}
