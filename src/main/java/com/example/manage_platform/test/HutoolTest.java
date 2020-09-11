package com.example.manage_platform.test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class HutoolTest {

    @Test
    public void testOne(){
//        String dateString = "2019-01-01";
//        Date date = Convert.toDate(dateString);
//        System.out.println(date);

        List<Object> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd", DateUtil.date(), 3.22676575765);
        List<Object> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1", DateUtil.date(), 250.7676);
        List<Object> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2", DateUtil.date(), 0.111);
        List<Object> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3", DateUtil.date(), 35);
        List<Object> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4", DateUtil.date(), 28.00);

        List<List<Object>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);

        BigExcelWriter writer= ExcelUtil.getBigWriter("e:/xxx.xlsx");
// 一次性写出内容，使用默认样式
        writer.write(rows);
// 关闭writer，释放内存
        writer.close();

        System.out.println("ceshi1");
    }
}
