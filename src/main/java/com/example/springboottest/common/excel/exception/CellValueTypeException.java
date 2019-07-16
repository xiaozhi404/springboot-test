package com.example.springboottest.common.excel.exception;


import com.example.springboottest.common.excel.utils.ExcelUtils;

/**
 * 若导入的单元格不是字符串类型，则抛出此异常
 */
public class CellValueTypeException extends ExcelUtilsException {

    public CellValueTypeException() {
    }

    public CellValueTypeException(String message) {
        super(message);
    }
}
