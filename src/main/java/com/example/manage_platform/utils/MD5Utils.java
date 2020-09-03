package com.example.manage_platform.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 */
public class MD5Utils {
     
    /**
     * 获取字符串的MD5
     * @param string 字符串
     * @return String MD5字符串
     * @throws NoSuchAlgorithmException
     */
    public static String getStringMD5(String string){
        StringBuffer sb = new StringBuffer();
        try {
            byte[] byteString=string.getBytes(Charset.forName("utf-8"));
            MessageDigest md= MessageDigest.getInstance("MD5");
            byte[] array=md.digest(byteString);
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
        }catch (NoSuchAlgorithmException e){

        }
        return sb.toString();
    }
     
    /**
     * 获取文件MD5
     * @param filePath 文件路径
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws DigestException
     */
    public static String getFileMD5(String filePath) throws NoSuchAlgorithmException, IOException, DigestException {
        MessageDigest md= MessageDigest.getInstance("MD5");
        int length=0;
        byte[] buffer=new byte[1024];
        byte[] array=null;
        InputStream is=new FileInputStream(new File(filePath));
        while((length=is.read(buffer))!=-1){
            md.update(buffer,0,length);
        }
         
        is.close();
         
        array=md.digest();
         
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
              sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
        }
         
        return sb.toString();
    }

    public static void main(String[] args)throws Exception {
        //String key = "L0re!2tm";
        String key = "abcd1234ABCD!#$%";
        String key1="Lorealcpd";
        String key2="abcd1234ABCD!#$%";
        String stringMD5 = getStringMD5(key);
        String keyscret=getStringMD5(key1);
        String keyscrets=getStringMD5(key2);
        System.out.println("加密前"+key);
        System.out.println("加密后"+stringMD5);
        System.out.println("加密前"+key1);
        System.out.println("加密后"+keyscret);
        System.out.println("加密前"+key2);
        System.out.println("加密后"+keyscrets);
        String 简单 = "123456";
        String 简单MD5 = getStringMD5(简单);
        System.out.println("加密后:"+简单MD5);
    }
}