package com.example.springboottest.admin.domain.dto;


import com.example.springboottest.common.excel.anno.ExcleColumn;import com.example.springboottest.common.excel.anno.ExcleSheet;import com.example.springboottest.common.excel.constant.ExcelColumType;
import lombok.Data;

import java.util.Date;

/**
 * 描述：考生excel模型
 */
@Data
@ExcleSheet(templateFileName = "/excel/导出考生账号.xlsx", startIndex = 2)
public class ExamineeExportModel {

    @ExcleColumn(index = 0, javaType = ExcelColumType.IDENTITY)
    private Integer no;

    @ExcleColumn(index = 1)
    private String name;

    @ExcleColumn(index = 2)
    private String sex;

    @ExcleColumn(index = 3)
    private String identityNum;

    @ExcleColumn(index = 4)
    private String mobile;

    @ExcleColumn(index = 5)
    private String grade;

    @ExcleColumn(index = 6)
    private String school;

    @ExcleColumn(index = 7)
    private String institution;

    @ExcleColumn(index = 8)
    private String submitTime;

    @ExcleColumn(index = 9)
    private Integer account;

}