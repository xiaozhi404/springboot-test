package com.example.springboottest.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 描述：可以用状态码的前几位代表部门线，项目
 */
public enum ExceptionMsgEnum {

    /**************************************************************
     * 一. 系统公用结果错误码定义区域【启始编号：10000，结束编号：20000】
     **************************************************************/
    /***
     * 系统错误
     */

    SYSTEM_ERROR(10001, "服务器错误", HttpStatus.SERVICE_UNAVAILABLE),
    NOT_FOUND(10002, "没有数据", HttpStatus.NOT_FOUND),
    BAD_PARAMS(10003, "缺少参数", HttpStatus.FORBIDDEN),
    TOKEN_EXPIRED(10004, "登录过期", HttpStatus.FORBIDDEN),
    PARAMS_TYPE_MISMATCH(10005, "参数类型不匹配", HttpStatus.FORBIDDEN),
    BAD_CREDENTIAL(10006, "登录态非法", HttpStatus.FORBIDDEN),
    BAD_GATEWAY(10007, "c端发送的异常信息体内容有误", HttpStatus.BAD_GATEWAY),
    CONVER_ERROR(10012, "实体之间的转化出现错误", HttpStatus.BAD_GATEWAY),
    PARAMS_IS_NULL(10013, "请求参数为空", HttpStatus.BAD_GATEWAY),
    PARAMS_NOT_VALID(10014, "请求参数格式错误", HttpStatus.BAD_GATEWAY),

    /**************************************************************
     * 二. 后台用户模块
     **************************************************************/
    ACCOUNT_NOT_FOUND(20001, "账号不存在", HttpStatus.FORBIDDEN),
    PASSWORD_WRONG(20002, "密码错误", HttpStatus.FORBIDDEN),
    ;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * http状态
     */
    private HttpStatus httpStatus;


    ExceptionMsgEnum(Integer code, String msg, HttpStatus httpStatus) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = httpStatus;
    }

    public Integer getCode() {

        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
