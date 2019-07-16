package com.example.springboottest.common.excel.constant;


/**
 * 描述：定义excel字段值的格式
 */
public enum ExcelColumType {
    STRING, INTEGER, DOUBLE, LONG, DATE,
    TIMESTAMP_S,    //秒值的时间戳
    TIMESTAMP_M,    //毫秒的时间戳
    LIST,       //动态导入，从startIndex开始进行赋值到最后
    NONE,       //创建一个空白单元格
    IDENTITY    //序号类型，自动创建序号
}
