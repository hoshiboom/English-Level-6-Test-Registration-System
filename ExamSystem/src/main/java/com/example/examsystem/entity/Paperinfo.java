package com.example.examsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.io.Serializable;
import java.util.Date;

//MyBatis ORM(Object-Relational Mapping)
@Data   //set、get
@NoArgsConstructor  //无参构造函数
@AllArgsConstructor //全参构造函数
@Accessors(chain =true)
public class Paperinfo implements Serializable {    //可序列化，方便保存对象
    @TableId(value = "id",type= IdType.AUTO)
    private Integer id;
    private String money;
    private String title;
    private String description;
    private String time;

    private static final long serialVersionUID = 1L;    //序列化和反序列化时校验防止出错，1L表示这是这个类的第一个版本

}
