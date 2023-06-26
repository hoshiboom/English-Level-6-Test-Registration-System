package com.example.examsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data   //set、get
@NoArgsConstructor  //无参构造函数
@AllArgsConstructor //全参构造函数
@Accessors(chain =true)
public class ScoreSum implements Serializable {

    Integer score;
    private static final long serialVersionUID = 1L;    //序列化和反序列化时校验防止出错，1L表示这是这个类的第一个版本
}
