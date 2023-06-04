package com.example.examsystem.exception;

import com.example.examsystem.dto.Response;
import com.example.examsystem.dto.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestController
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 指定能处理的异常类型
    @ExceptionHandler(DaoException.class)
    public Response daoExceptionHandler(DaoException e) {
        e.printStackTrace();
        return new Response(e.getResponseEnum());
    }

    @ExceptionHandler(Exception.class)//指定能处理的异常类型
    public Response exceptionHandler(Exception e) {
        e.printStackTrace();
        return new Response(ResponseEnum.TEST_TEST);
    }
}
