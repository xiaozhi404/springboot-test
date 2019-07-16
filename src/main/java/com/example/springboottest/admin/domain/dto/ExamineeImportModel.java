package com.example.springboottest.admin.domain.dto;


import com.example.springboottest.common.excel.anno.ExcleColumn;
import com.example.springboottest.common.excel.anno.ExcleColumnVerify;
import com.example.springboottest.common.excel.anno.ExcleSheet;
import com.example.springboottest.common.excel.constant.ExcelColumType;
import lombok.Data;
import org.omg.PortableInterceptor.INACTIVE;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述：考生excel模型
 */
@Data
@ExcleSheet(titleIndex = 1, startIndex = 2, importBlankRow = false, ignoreOnlyHaveNoRow = 0)
public class ExamineeImportModel {

    @ExcleColumn(index = 1, javaType = ExcelColumType.STRING)
    private String name;

    @ExcleColumn(index = 2, javaType = ExcelColumType.STRING)
    private String sex;

    @ExcleColumn(index = 3, javaType = ExcelColumType.STRING)
    @ExcleColumnVerify(notNull = true, isIDCardNo = true, only = true)
    private String identityNum;

    @ExcleColumn(index = 4, javaType = ExcelColumType.LONG)
    @ExcleColumnVerify(notNull = true, isPhoneNum = true)
    private Long mobile;

    @ExcleColumn(index = 5, javaType = ExcelColumType.STRING)
    private String grade;

    @ExcleColumn(index = 6, javaType = ExcelColumType.STRING)
    private String school;

    @ExcleColumn(index = 7, javaType = ExcelColumType.STRING)
    private String institution;

    @ExcleColumn(index = 8, javaType = ExcelColumType.TIMESTAMP_S, dateFormat = "yyyy/MM/dd")
    private Integer submitTime;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

}