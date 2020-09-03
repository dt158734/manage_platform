package com.example.manage_platform.constant;

/**
 * Created by haiSheng on 2020/4/21.
 */
public class DataSourceHolder {

    private static final ThreadLocal<String> datasourcce = new ThreadLocal<String>();

    public static void setCustomeType(String type){
        datasourcce.set(type);
    }

    public static String getCustomeType(){
        String source = datasourcce.get();
        if(source == null || source.isEmpty()){
            return Constants.READDATASOURCE;
        }
        return source;
    }

    public static void remove(){
        datasourcce.remove();
    }

}
