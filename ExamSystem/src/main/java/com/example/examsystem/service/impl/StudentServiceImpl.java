package com.example.examsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import com.example.examsystem.entity.Student;
import com.example.examsystem.exception.DaoException;
import com.example.examsystem.mapper.StudentMapper;
import com.example.examsystem.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service    //bean
//ServiceImpl是mybatis-plus的类，默认实现IService
//StudentServiceImpl类是服务实现类的一个实例，提供StudentService接口中定义的业务逻辑的实际实现，使用StudentMapper类与数据库交互并对Student记录执行CRUD操作。
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Resource
    private StudentMapper studentMapper;
    @Override
    public Student login(Integer number, String password) {
        //调用mapper dao层功能
        return studentMapper.selectByNumberAndPassword(number, password);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response insertStudent(Student student){
        if(student.getNumber()!=null) {
            Student Student1 = this.getOne(new QueryWrapper<Student>().eq("number", student.getNumber()));
            if (Student1!= null) {
                return new Response(ResponseEnum.Add_Student_Failure);
            }
        }
        studentMapper.insert(student);
        return new Response(ResponseEnum.Add_Student_Success);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response updateStudent(Student Student){
        Student Studenttest = this.getOne(
                new QueryWrapper<Student>().eq("id",Student.getId())
        );
        if (Studenttest == null) return new Response(ResponseEnum.Update_Student_Failure);
        if(!this.updateById(Student)){
            throw new DaoException(ResponseEnum.Update_Student_Failure);
        }
        return new Response(ResponseEnum.Update_Student_Success);
    }

    @Override
    public Response deleteStudent(Integer id) {
        Student Studenttest = this.getOne(
                new QueryWrapper<Student>().eq("id",id)
        );
        if (Studenttest == null) return new Response(ResponseEnum.Delete_Student_Failure);
        if(!this.removeById(Studenttest)){
            throw new DaoException(ResponseEnum.Delete_Student_Failure);
        }
        return new Response(ResponseEnum.Delete_Student_Success);
    }
}
