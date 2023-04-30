package com.example.examsystem.exception;

import com.example.examsystem.dto.ResponseEnum;
import lombok.Getter;

@Getter
public class DaoException extends RuntimeException{
    private final ResponseEnum responseEnum;

    public DaoException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.responseEnum = responseEnum;
    }

    public DaoException(ResponseEnum responseEnum, Throwable cause) {
        super(responseEnum.getMsg(), cause);
        this.responseEnum = responseEnum;
    }

}
