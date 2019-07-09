package com.example.springboottest.common.exception.dto;

import com.example.springboottest.common.exception.ExceptionMsgEnum;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private ExceptionMsgEnum exceptionMsgEnum;

    public BusinessException(ExceptionMsgEnum exceptionMsgEnum, Throwable throwable) {
        super(throwable);
        this.exceptionMsgEnum = exceptionMsgEnum;
    }
}
