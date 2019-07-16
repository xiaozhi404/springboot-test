package com.example.springboottest.common.excel.anno;


import com.example.springboottest.common.excel.constant.ExcelHeadType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * 描述：定义excle头信息
 * ps: 利用横坐标和纵坐标定位,在业务model修饰,由于终点的标题头和单元格值数量未定，所以是动态导入
 */
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcleHead {

    int index();

    int titleRowIndex();

    ExcelHeadType headType();

}
