package com.example.examsystem.controler;

import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Admin;
import com.example.examsystem.mapper.AdminMapper;
import com.example.examsystem.service.AdminService;
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
    private AdminMapper adminMapper;

    @ResponseBody
    @PostMapping("/login/a1")  //admin，调用admin业务层service即可
    public Response login(@NonNull @RequestParam("number")Integer number,
                          @NonNull @RequestParam("password")String password) {

        Admin a = adminService.login(number, password);
        if (a != null) {
            Map<String , Object> claims = new HashMap<>();
            claims.put("id", a.getId());
            claims.put("roleId", a.getRoleId());
            claims.put("username",a.getUsername());
            return new Response(ResponseEnum.Login_Success,JwtUtils.generateToken(claims));
        }
        else{
            return new Response(ResponseEnum.Login_Failure);
        }

    }


}
