package com.example.springboottest.common.exception;


import com.example.springboottest.common.exception.dto.BusinessException;
import com.example.springboottest.common.exception.dto.ExceptionResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 描述：全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseBody> exceptionHandle(Exception e) {
        ExceptionMsgEnum errorMsg = ExceptionMsgEnum.SYSTEM_ERROR;
        return new ResponseEntity<>(
                new ExceptionResponseBody(
                        errorMsg.getCode(),
                        errorMsg.getMsg()
                ),
                errorMsg.getHttpStatus()
        );
    }


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionResponseBody> businessHandle(BusinessException e) {
        ExceptionMsgEnum exceptionMsgEnum = e.getExceptionMsgEnum();
        return new ResponseEntity<>(
                new ExceptionResponseBody(exceptionMsgEnum.getCode(), exceptionMsgEnum.getMsg()),
                exceptionMsgEnum.getHttpStatus()
        );

    }


}
