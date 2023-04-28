package com.example.examsystem.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//MyBatis ORM(Object-Relational Mapping)
@Data   //set、get参数
@NoArgsConstructor  //无参构造函数
@AllArgsConstructor //全参构造函数
public class Admin {
    @TableId    //id是主键
    private Integer id;
    private String username;
    private String password;
    private String email;


}
