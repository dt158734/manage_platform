package com.example.manage_platform.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

/**
 * @author dongtao 2020年4月26日11:44:45
 */
public class HandProperties {


    /**
     * 读取配置文件
     * @param string
     * @return
     */
    public static String getProperties(String string) {
        String str = "";
        BufferedReader bufferedReader = null;
        try {
            Properties properties = new Properties();
            bufferedReader = new BufferedReader(new FileReader("/usr/local/tomcat/olya-buycoor-pc/conf/url.properties"));
            properties.load(bufferedReader);
            str = properties.getProperty(string);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception e) {

            }
        }
        return str;
    }

}
