package com.example.springboottest.admin.domain.dto;


import com.example.springboottest.common.excel.anno.ExcleHead;
import com.example.springboottest.common.excel.constant.ExcelHeadType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 描述：定义动态导出的标题头
 * 注意：类型要与对应的标题头类型一致
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamineeExportHead {

    @ExcleHead(index = 10, titleRowIndex = 0, headType = ExcelHeadType.MERGE_HEAD)
    private LinkedHashMap<String, Integer> mergeHeads;

    @ExcleHead(index = 10, titleRowIndex = 1, headType = ExcelHeadType.LIST_HEAD)
    private List<String> headList;

}