package com.example.examsystem.controler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Admin;
import com.example.examsystem.exception.DaoException;
import com.example.examsystem.mapper.AdminMapper;
import com.example.examsystem.service.AdminService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@Validated
@CrossOrigin
@RestController //Restful风格
public class AdminController {

    @Resource
    private AdminService adminService;

    @Resource
    private AdminMapper adminMapper;


    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public Response adminPage(@RequestParam(required = false) Integer curpage,
                          @RequestParam(required = false) Integer size) {
        if (curpage == null || curpage <= 0) curpage = 1;
        if (size == null || size <= 0 || size > 10) size = 10;

        Page<Admin> page=adminService.page(new Page<>(curpage,size),new QueryWrapper<Admin>().select("id","name","password","number","email"));
        return new Response(ResponseEnum.List_Admin_Success,page);

    }

    @RequestMapping(value = "/admin/{id}",method = RequestMethod.GET)
    public Response getAdminById(@PathVariable("id") Integer id) {

        Admin admin = adminService.getOne(
                new QueryWrapper<Admin>().eq("id",id)
        );
        if (admin == null) return new Response(ResponseEnum.Get_Admin_Failure);
        return new Response(ResponseEnum.Get_Admin_Success,admin);
    }

    @RequestMapping(value = "/admin",method = RequestMethod.POST)
    public Response addAdmin(@NonNull @RequestParam("name") String name,
                             @NonNull @RequestParam("password") String password,
                             @RequestParam("number") Integer number,
                             @RequestParam("email") String email){
        Admin admin = new Admin().setName(name).setPassword(password).setEmail(email).setNumber(number);

        return adminService.insertAdmin(admin);
    }

    @RequestMapping(value = "/admin/{id}",method = RequestMethod.PUT)
    public Response updateAdmin(@PathVariable("id") Integer id,
                             @NonNull @RequestParam("name") String name,
                             @NonNull @RequestParam("password") String password,
                             @RequestParam("number") Integer number,
                             @RequestParam("email") String email){

        Admin admin = new Admin().setId(id).setName(name).setPassword(password).setEmail(email).setNumber(number);

        return adminService.updateAdmin(admin);
    }
    @RequestMapping(value = "/admin/{id}",method = RequestMethod.DELETE)
    public Response deleteAdmin(@PathVariable("id") Integer id){
        return adminService.deleteAdmin(id);
    }
}
