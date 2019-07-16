package com.example.springboottest.common.excel.constant;


/**
 * 描述：导出时，定义标题头
 */
public enum ExcelHeadType {
    HEAD, //普通的标题头,即一个单元格对应一个标题头
    LIST_HEAD,//顺序创建标题头
    MERGE_HEAD  //创建合并的标题头
}
