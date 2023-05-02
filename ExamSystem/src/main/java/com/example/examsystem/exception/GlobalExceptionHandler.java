package com.example.examsystem.exception;

import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)//指定能处理的异常类型
    public Response exceptionHandler(Exception e) {

        e.printStackTrace();
        return new Response(ResponseEnum.TEST_TEST);
    }
}
