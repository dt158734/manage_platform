package com.example.manage_platform.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class ReadExcelUtils {
    /**
     * 读EXCEL文件，获取信息集合
     *
     * @param mFile
     * @return
     */
    public List<?> getExcelInfo(MultipartFile mFile) {
        // 获取文件名
        String fileName = mFile.getOriginalFilename();
        try {
            // 验证文件名是否合格
            if (!validateExcel(fileName)) {
                return null;
            }
            // 根据文件名判断文件是2003版本还是2007版本
            boolean isExcel2003 = true;
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            return createExcel(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据excel里面的内容读取客户信息
     *
     * @param is      输入流
     * @param isExcel2003   excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public List<?> createExcel(InputStream is, boolean isExcel2003) {
        try {
            Workbook wb = null;
            if (isExcel2003) {
                // 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {
                // 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            // 读取Excel里面客户的信息
            return readExcelValue(wb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取Excel里面客户的信息
     *
     * @param wb
     * @return
     */
    private List<?> readExcelValue(Workbook wb) {

        // 总行数
        int totalRows = 0;
        // 总条数
        int totalCells = 0;
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 6 && sheet.getRow(6) != null) {
            totalCells = sheet.getRow(6).getPhysicalNumberOfCells();
        }
        List<Map<Integer, Object>> objectArrayList = new ArrayList<>();
        // 循环Excel行数
        for (int r = 7; r <= totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                // 当行为null是直接放弃!不在进行查询
                break;
            }
            // 可以补充其他的方法!调用创建成一个Map的name对象,传进来之后把他替换掉!
            // 然后根据c的位数和map中的位数进行判断
            Map<Integer,Object> map = new LinkedHashMap<>(1);
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                String stringCellValue = cell.getStringCellValue().trim();
                System.out.println(stringCellValue);
                map.put(c,stringCellValue);
            }
            objectArrayList.add(map);
        }
        return objectArrayList;
    }

    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            log.error("文件名不是excel格式");
            return false;
        }
        return true;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    // @描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
