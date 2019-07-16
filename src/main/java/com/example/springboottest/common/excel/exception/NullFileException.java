package com.example.springboottest.common.excel.exception;

/**
 * 空文件异常
 */
public class NullFileException  extends ExcelUtilsException  {

    public NullFileException() {
    }

    public NullFileException(String message) {
        super(message);
    }
}
