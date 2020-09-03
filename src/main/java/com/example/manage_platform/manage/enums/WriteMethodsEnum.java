package com.example.manage_platform.manage.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haiSheng on 2020/6/1.
 */
public enum WriteMethodsEnum {

    WriteMethods_1("com.example.manage_platform.manage.dao.HelloUserDao");

    WriteMethodsEnum(String method){
        this.method = method;
    }

    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public static List<String> getAllWriteMethods(){
        List<String> methodLists = new ArrayList<>();
        for(WriteMethodsEnum writeMethods : WriteMethodsEnum.values()){
            String method = writeMethods.getMethod();
            methodLists.add(method);
        }
        return methodLists;
    }

}
