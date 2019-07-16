package com.example.springboottest.admin.controller;


import com.example.springboottest.admin.domain.dto.ExamineeDynamicExportModel;
import com.example.springboottest.admin.domain.dto.ExamineeExportHead;
import com.example.springboottest.admin.domain.dto.ExamineeExportModel;
import com.example.springboottest.admin.domain.dto.ExamineeImportModel;
import com.example.springboottest.common.excel.utils.ExcelUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.javassist.bytecode.ByteArray;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * 描述：excel导入导出测试
 */
@RestController
@RequestMapping("/admin/excel")
public class ExcelController {


    @ApiOperation("导入考生名单,使用resource目录下的*导入考生名单.xlsx进行测试")
    @PostMapping("/import")
    public void importExaminees(MultipartFile file) throws Exception {
        List<ExamineeImportModel> examineeImportModels = ExcelUtils.covertExcel2Model(file, ExamineeImportModel.class);
        System.out.println(examineeImportModels);
    }

    @ApiOperation("导出考生账号，普通导出")
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportExaminees() throws Exception {
        List<ExamineeExportModel> list = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            ExamineeExportModel e = new ExamineeExportModel();
            e.setGrade("一年级");
            e.setSex("男");
            e.setIdentityNum("4444" + i);
            e.setInstitution("hello");
            e.setMobile("12344");
            e.setName("hello");
            e.setSchool("编程猫");
            e.setSubmitTime("2013/11/11");
            e.setAccount(888+i);
            list.add(e);
        }
        return ExcelUtils.export(list, null,"导出考生账号");
    }

    @ApiOperation("导出考生账号，动态导出，即动态生成标题头和对应单元格值")
    @GetMapping("/export/dynamic")
    public ResponseEntity<byte[]> exportExamineesDynamic() throws Exception {
        List<ExamineeDynamicExportModel> list = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            ExamineeDynamicExportModel e = new ExamineeDynamicExportModel();
            e.setGrade("一年级");
            e.setSex("男");
            e.setIdentityNum("4444" + i);
            e.setInstitution("hello");
            e.setMobile("12344");
            e.setName("hello");
            e.setSchool("编程猫");
            e.setSubmitTime("2013/11/11");
            e.setAccount("xiaozhi"+i);
            ArrayList<Integer> scores = new ArrayList<>();
//            scores.add("10");
//            scores.add("20");
//            scores.add("30");
//            scores.add("40");
//            scores.add("50");
            scores.add(10);
            scores.add(20);
            scores.add(30);
            scores.add(40);
            scores.add(50);
            e.setScores(scores);
            list.add(e);
        }
        ExamineeExportHead examineeExportHead = new ExamineeExportHead();
        LinkedHashMap<String, Integer> mergeHeads = new LinkedHashMap<>();
        mergeHeads.put("模拟考试", 5);
        ArrayList<String> questions = new ArrayList<>();
        questions.add("1");
        questions.add("2");
        questions.add("3");
        questions.add("4");
        questions.add("5");
        examineeExportHead.setHeadList(questions);
        examineeExportHead.setMergeHeads(mergeHeads);

        return ExcelUtils.export(list, examineeExportHead,"动态导出考生账号和成绩");
    }


}

