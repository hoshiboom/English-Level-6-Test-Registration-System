package com.example.examsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.examsystem.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

//在这个接口里也可以自己实现复杂的sql语句
@Mapper //MyBatis注解，Mapper指示这是一个mapper接口，映射到sql语句，BaseMapper接口是包含CRUD的通用接口
@Repository //Spring注解，Repository指示这个接口与数据库交互，spring将会自动生成一个AdminMapper实例作为spring bean，可以将其注入到依赖它的其他组件中
public interface AdminMapper extends BaseMapper<Admin> {
    @Select("select * from admin where name = #{name}")
    Admin selectByName(String name);

    @Select("select * from admin where number = #{number} and password = #{password}")
    Admin selectByNumberAndPassword(Integer number, String password);


}
