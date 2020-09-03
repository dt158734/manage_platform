package com.example.manage_platform.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/** 
 * 汉字转化为拼音的工具类 
 * @author andy
 * 
 */  
public class PinyinTool {  
    HanyuPinyinOutputFormat format = null;  
    public static enum Type {
        UPPERCASE,              //全部大写  
        LOWERCASE,              //全部小写  
        FIRSTAllPPER,              //全部小写
        FIRSTUPPER              //首字母大写
    }  
  
    public PinyinTool(){  
        format = new HanyuPinyinOutputFormat();  
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
    }  
  
    public String myPinYin(String str) {
        return toPinYin(str, "", Type.FIRSTAllPPER);
    }
    public String toPinYin(String str) {
        return toPinYin(str, "", Type.LOWERCASE);
    }
  
    public String toPinYin(String str, String spera) {
        return toPinYin(str, spera, Type.LOWERCASE);
    }  
  
    /** 
     * 将str转换成拼音，如果不是汉字或者没有对应的拼音，则不作转换 
     * 如： 明天 转换成 MINGTIAN 
     * @param str：要转化的汉字 
     * @param spera：转化结果的分割符 
     * @return 
     * @throws BadHanyuPinyinOutputFormatCombination 
     */  
    public String toPinYin(String str, String spera, Type type)  {
        if(str == null || str.trim().length()==0)  
            return "";  
        if(type == Type.UPPERCASE) {
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        }
        else {
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        }
        String py = "";
        String temp = "";
        String[] t;
        try {
            for(int i=0;i<str.length();i++){
                char c = str.charAt(i);
                if((int)c <= 128) {
                    py += c;
                }
                else{
                    t = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    if(t == null) {
                        py += c;
                    }
                    else{
                        temp = t[0];
                        if(type == Type.FIRSTUPPER) {
                            temp = t[0].toUpperCase().charAt(0) + temp.substring(1);
                        }
                        if(type == Type.FIRSTAllPPER) {
                            if(i == 0){
                                temp = t[0].toUpperCase().charAt(0) + temp.substring(1);
                            }
                        }
                        py += temp + (i == str.length() - 1 ? "" : spera);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return py.trim();  
    }

    /**
     * 判断输入的是否为中文
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        boolean flag = false;
        if (str != null) {
            String regEx = "[\u4e00-\u9fa5]";
            Pattern pat = Pattern.compile(regEx);
            Matcher matcher = pat.matcher(str);
            if (matcher.find()) {
                flag = true;
            }
        }
        return flag;
    }

    public static void main(String[] args) throws Exception {
        PinyinTool pinyinTool = new PinyinTool();
        System.out.println("测试结果为====" + pinyinTool.myPinYin("上海市"));
    }
}  