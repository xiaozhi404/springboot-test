package com.example.springboottest.common.excel.exception;


/**
 * 若导入的单元格不是字符串类型，则抛出此异常
 */
public class ExcelUtilsException extends RuntimeException {

    public ExcelUtilsException() {
    }

    public ExcelUtilsException(String message) {
        super(message);
    }
}
