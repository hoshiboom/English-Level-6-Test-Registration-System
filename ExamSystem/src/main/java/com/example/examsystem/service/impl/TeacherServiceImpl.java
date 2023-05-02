package com.example.examsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Teacher;
import com.example.examsystem.exception.DaoException;
import com.example.examsystem.mapper.TeacherMapper;
import com.example.examsystem.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service    //bean
//ServiceImpl是mybatis-plus的类，默认实现IService
//TeacherServiceImpl类是服务实现类的一个实例，提供TeacherService接口中定义的业务逻辑的实际实现，使用TeacherMapper类与数据库交互并对Teacher记录执行CRUD操作。
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;
    @Override
    public Teacher login(Integer number, String password) {
        //调用mapper dao层功能
        return teacherMapper.selectByNumberAndPassword(number, password);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response insertTeacher(Teacher teacher){
        if(teacher.getNumber()!=null) {
            Teacher Teacher1 = this.getOne(new QueryWrapper<Teacher>().eq("number", teacher.getNumber()));
            if (Teacher1!= null) {
                return new Response(ResponseEnum.Add_Teacher_Failure);
            }
        }
        teacherMapper.insert(teacher);
        return new Response(ResponseEnum.Add_Teacher_Success);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response updateTeacher(Teacher teacher){
        Teacher Teachertest = this.getOne(
                new QueryWrapper<Teacher>().eq("id",teacher.getId())
        );
        if (Teachertest == null) return new Response(ResponseEnum.Update_Teacher_Failure);
        if(!this.updateById(teacher)){
            throw new DaoException(ResponseEnum.Update_Teacher_Failure);
        }
        return new Response(ResponseEnum.Update_Teacher_Success);
    }

    @Override
    public Response deleteTeacher(Integer id) {
        Teacher Teachertest = this.getOne(
                new QueryWrapper<Teacher>().eq("id",id)
        );
        if (Teachertest == null) return new Response(ResponseEnum.Delete_Teacher_Failure);
        if(!this.removeById(Teachertest)){
            throw new DaoException(ResponseEnum.Delete_Teacher_Failure);
        }
        return new Response(ResponseEnum.Delete_Teacher_Success);
    }
}
