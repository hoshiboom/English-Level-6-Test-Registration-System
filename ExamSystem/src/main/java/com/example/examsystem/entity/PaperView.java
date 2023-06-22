package com.example.examsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data   //set、get
@NoArgsConstructor  //无参构造函数
@AllArgsConstructor //全参构造函数
@Accessors(chain =true)
public class PaperView {
    private Integer id;
    private String questionName;
    private String questionDescription;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private Integer typeId;
    private Integer score;
    private Integer questionId;
    private Integer paperinfoId;
    private Integer orderId;


}

