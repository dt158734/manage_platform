package com.example.manage_platform.test;

import cn.hutool.core.convert.Convert;
import org.junit.Test;

import java.util.Date;

public class HutoolTest {

    @Test
    public void testOne(){
        String dateString = "2019-01-01";
        Date date = Convert.toDate(dateString);
        System.out.println(date);
    }
}
