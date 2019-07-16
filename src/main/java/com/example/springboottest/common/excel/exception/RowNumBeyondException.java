package com.example.springboottest.common.excel.exception;

/**
 * 描述：当导入的行数超过设置的最大值时，抛出此异常
 */
public class RowNumBeyondException extends ExcelUtilsException {

    public RowNumBeyondException() {
    }

    public RowNumBeyondException(String message) {
        super(message);
    }
}
