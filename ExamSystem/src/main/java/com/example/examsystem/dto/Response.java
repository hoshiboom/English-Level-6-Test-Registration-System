package com.example.examsystem.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@Accessors(chain=true)  //setter的注解
public class Response {
    private Integer code;
    private String msg;
    @SerializedName(value = "data")//Gson的注解，用于data为null时的正确序列化
    private Object data;
    private Date date;
    @SerializedName(value = "token")
    private String token;
    public Response(ResponseEnum responseEnum, Object data) {
        this.msg = responseEnum.getMsg();
        this.code = responseEnum.getCode();
        this.data = data;
        this.date = new Date();
        this.token = null;
    }
    public Response(ResponseEnum responseEnum) {
        this.msg = responseEnum.getMsg();
        this.code = responseEnum.getCode();
        this.data = null ;
        this.date = new Date();
        this.token = null;
    }
    public Response(ResponseEnum responseEnum, String jwt) {
        this.msg = responseEnum.getMsg();
        this.code = responseEnum.getCode();
        this.data = null ;
        this.date = new Date();
        this.token = jwt;
    }

}
