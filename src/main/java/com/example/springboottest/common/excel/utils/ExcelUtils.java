package com.example.springboottest.common.excel.utils;



import com.example.springboottest.common.excel.anno.ExcleColumn;
import com.example.springboottest.common.excel.anno.ExcleColumnVerify;
import com.example.springboottest.common.excel.anno.ExcleHead;
import com.example.springboottest.common.excel.anno.ExcleSheet;
import com.example.springboottest.common.excel.constant.ExcelHeadType;
import com.example.springboottest.common.excel.exception.CellValueTypeException;
import com.example.springboottest.common.excel.exception.NotExcelException;
import com.example.springboottest.common.excel.exception.NullFileException;
import com.example.springboottest.common.excel.exception.RowNumBeyondException;
import lombok.val;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;


public class ExcelUtils {

    /**
     * 解决格式转化和总行数读取不准确的问题
     * 1、规定导入的excel的全部单元格格式为文本
     * 2、bug,若空白行有格式，会让读取的行数虚高，应该删除多余行或者清除格式之后进行导入
     * sheet.getLastRowNum()获取的是最后下标，sheet.getPhysicalNumberOfRows()获取的是总行数
     */
    public static <T> List<T> covertExcel2Model(MultipartFile file, Class<T> clazz) throws Exception {

        checkExcleFile(file);
        ExcleSheet excleSheet = clazz.getAnnotation(ExcleSheet.class);
        List result = new ArrayList<T>();
        Workbook wb = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = wb.getSheetAt(0);
        if ((sheet.getLastRowNum()+1 - excleSheet.startIndex()) > excleSheet.maxRowNum()) {
            throw new RowNumBeyondException("导入的行数超过最大值:" + excleSheet.maxRowNum());
        }

        //初始化标题名和下标
        HashMap<Integer, String> indexWithTitle = new HashMap<>();
        Row titleRow = sheet.getRow(excleSheet.titleIndex());
        int titleCellNum = titleRow.getPhysicalNumberOfCells();
        for (int i = 0; i < titleCellNum; ++i) {
            indexWithTitle.put(i, titleRow.getCell(i).getStringCellValue());
        }
        //唯一性值，用index+字段的string值进行存储
        List<String> onlyContainer = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() < excleSheet.startIndex() || isBlankRow(excleSheet.ignoreOnlyHaveNoRow(), row)) {
                continue;
            }
            T t = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                ExcleColumn excleColumn = field.getAnnotation(ExcleColumn.class);
                if (null == excleColumn) continue;
                int index = excleColumn.index();
                Cell cell = row.getCell(index);
                String cellValue;
                try {
                    cellValue= cell.getStringCellValue();
                } catch (Exception e) {
                    throw new CellValueTypeException("请将excel的单元格全部转成文本格式");
                }
                switch (excleColumn.javaType()) {
                    case STRING:
                        MyBeanUtils.setProperty(t, field.getName(), cellValue);
                        break;
                    case INTEGER:
                        MyBeanUtils.setProperty(t, field.getName(), Integer.valueOf(cellValue));
                        break;
                    case LONG:
                        MyBeanUtils.setProperty(t, field.getName(), Long.valueOf(cellValue));
                        break;
                    case DOUBLE:
                        MyBeanUtils.setProperty(t, field.getName(), Double.valueOf(cellValue));
                        break;
                    case DATE:
                        SimpleDateFormat formatDate = new SimpleDateFormat(excleColumn.dateFormat());
                        MyBeanUtils.setProperty(t, field.getName(), formatDate.parse(cellValue));
                        break;
                    case TIMESTAMP_S:
                        SimpleDateFormat formatS = new SimpleDateFormat(excleColumn.dateFormat());
                        MyBeanUtils.setProperty(t, field.getName(), (int)formatS.parse(cellValue).getTime()/1000);
                        break;
                    case TIMESTAMP_M:
                        SimpleDateFormat formatM = new SimpleDateFormat(excleColumn.dateFormat());
                        MyBeanUtils.setProperty(t, field.getName(), formatM.parse(cellValue).getTime());
                        break;
                }
                //校验
                ExcleColumnVerify excleColumnVerify = field.getAnnotation(ExcleColumnVerify.class);

                if (null != excleColumnVerify) {
                    Object propVal = MyBeanUtils.getProperty(t, field.getName());
                    ExcelColumnVerifyUtils.verity(propVal, row.getRowNum()+1, indexWithTitle.get(index), excleColumnVerify, onlyContainer, index);
                }
            }
            result.add(t);
        }
        return result;
    }


    /**
     * 判断是否是空白行
     * @param noIndex
     * @param row
     * @return
     */
    public static Boolean isBlankRow(int noIndex, Row row) {
        int cellNum = row.getPhysicalNumberOfCells();
        for (int i = 0; i < cellNum; ++i) {
            Cell c = row.getCell(i);
            if (i != noIndex && c != null && c.getCellTypeEnum() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    /**
     * 描述：导出excel,使用模版+模型列表的方式
     * PS: 全部值按照string类型进行返回
     */
    public static<T> ResponseEntity<byte[]> export(List<T> modelList, Object headModel, String fileName) throws Exception {
        if (null == modelList || modelList.size() < 1) {
            return null;
        }
        Class<T> clazz = (Class<T>) modelList.get(0).getClass();
        //获取导出的excel模版
        ExcleSheet excleSheet = clazz.getAnnotation(ExcleSheet.class);
        String templateFileName = excleSheet.templateFileName();
        InputStream resourceAsStream = ExcelUtils.class.getResourceAsStream(templateFileName);
        Workbook wb = WorkbookFactory.create(resourceAsStream);
        Sheet sheet = wb.getSheetAt(0);
        //获取单元格样式
        CellStyle cellStyle = sheet.getRow(excleSheet.startIndex()).getCell(0).getCellStyle();
        Integer counter = excleSheet.startIndex();
        //id生成器
        Double idCounter = 1d;
        //判断是否需要生成标题头
        if (null != headModel) {
            generateTitleHead(sheet, excleSheet, headModel);
        }

        for (T t: modelList) {
            Row row = sheet.createRow(counter++);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                ExcleColumn excleColumn = field.getAnnotation(ExcleColumn.class);
                if (null == excleColumn) continue;
                int index = excleColumn.index();
                Cell cell;
                switch (excleColumn.javaType()) {
                    case NONE:
                        cell = row.createCell(index);
                        cell.setCellStyle(cellStyle);
                        break;
                    case LIST:
                        String name = field.getName();
                        List list = (ArrayList) MyBeanUtils.getProperty(t, name);
                        for (int i = 0; i < list.size(); ++i) {
                            cell = row.createCell(index++);
                            cell.setCellStyle(cellStyle);
                            cell.setCellValue(list.get(i).toString());
                        }
                        break;
                    case IDENTITY:
                            cell = row.createCell(index);
                            cell.setCellStyle(cellStyle);
                            cell.setCellValue(idCounter++);
                        break;
                    default:
                        cell = row.createCell(index);
                        cell.setCellStyle(cellStyle);
                        String fieldName = field.getName();
                        Object o = MyBeanUtils.getProperty(t, fieldName);
                        if (null== o) break;
                        cell.setCellValue(o.toString());
                        break;
                }
            }
        }
        //写出
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/x-download");
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode(fileName+".xlsx", "UTF-8"));
        val outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        return new ResponseEntity(outByteStream.toByteArray(), headers, HttpStatus.OK);
    }

    /**
     * 动态创建标题头
     */
    public static void generateTitleHead(Sheet sheet, ExcleSheet excleSheet, Object headModel) throws Exception {
        //以第一个标题单元格作为标题样式
        val titleCellStyle = sheet.getRow(excleSheet.titleIndex()).getCell(0).getCellStyle();
        Field[] fields = headModel.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            ExcleHead excleHead = field.getAnnotation(ExcleHead.class);
            ExcelHeadType excelHeadType = excleHead.headType();
            int titleRowIndex = excleHead.titleRowIndex();
            Row titleRow = sheet.getRow(titleRowIndex);
            int index = excleHead.index();
            switch (excelHeadType)  {
                case HEAD:
                    String headVal = (String)MyBeanUtils.getProperty(headModel, field.getName());
                    Cell cell = titleRow.createCell(index);
                    cell.setCellStyle(titleCellStyle);
                    cell.setCellValue(headVal);
                    break;
                case LIST_HEAD:
                    List<String> headVals = (List<String>)MyBeanUtils.getProperty(headModel, field.getName());
                    for (String val: headVals) {
                       Cell listCell = titleRow.createCell(index++);
                       listCell.setCellStyle(titleCellStyle);
                       listCell.setCellValue(val);
                    }
                    break;
                case MERGE_HEAD:
                    LinkedHashMap<String, Integer> mergeValues = (LinkedHashMap<String, Integer>) MyBeanUtils.getProperty(headModel, field.getName());
                    for (Map.Entry<String, Integer> mergeVal: mergeValues.entrySet()) {
                       Cell mergeCell = titleRow.createCell(index);
                       mergeCell.setCellStyle(titleCellStyle);
                       mergeCell.setCellValue(mergeVal.getKey());
                       if (mergeVal.getValue() > 1) {
                           sheet.addMergedRegion(new CellRangeAddress(titleRowIndex, titleRowIndex, index, index+mergeVal.getValue()-1));
                       }
                       index += mergeVal.getValue();
                    }
                    break;
            }

        }
      }


    /**
     * 检查excel文件的合法性
     * @param file
     */
    public static void checkExcleFile(MultipartFile file) {
        if (null == file) {
            throw new NullFileException();
        }
        //判断是否为excel文件
        String filename = "";
        if (!filename.endsWith(".xls") && !filename.endsWith(".xlsx")) {
            throw new NotExcelException();
        }
    }
}

