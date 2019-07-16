package com.example.springboottest.admin.domain.dto;


import com.example.springboottest.common.excel.anno.ExcleColumn;
import com.example.springboottest.common.excel.anno.ExcleSheet;
import com.example.springboottest.common.excel.constant.ExcelColumType;
import lombok.Data;

/**
 * 描述：考生excel模型
 */
@Data
@ExcleSheet(templateFileName = "/excel/导出考生账号.xlsx", startIndex = 2)
public class ExamineeHeadExportModel {

    @ExcleColumn(index = 0, javaType = ExcelColumType.IDENTITY)
    private Integer no;


}