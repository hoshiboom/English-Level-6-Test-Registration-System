package com.example.examsystem.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

//MyBatis ORM(Object-Relational Mapping)
@Data   //set、get
@NoArgsConstructor  //无参构造函数
@AllArgsConstructor //全参构造函数
@Accessors(chain =true)
public class Admin implements Serializable {    //可序列化，方便保存对象
    private Integer id;
    private String username;
    private String password;
    @TableId    //主键
    private Integer number;//工号
    private String roleId;//角色id
    private String email;

    private static final long serialVersionUID = 1L;    //序列化和反序列化时校验防止出错，1L表示这是这个类的第一个版本

}
