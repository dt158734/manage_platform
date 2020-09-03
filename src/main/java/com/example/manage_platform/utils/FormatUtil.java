package com.example.manage_platform.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Double型计算
 *
 * @author hub
 * @version 1.0 2011.09.28
 */
public class FormatUtil {
    private static final int DEF_DIV_SCALE = 10;
    /**
     * 计算中间值保留位数
     */
    public static final int TEMP_VALUE_LENGTH = 6;

    /**
     * 两个double型相减
     *
     * @param v1
     * @param v2
     * @return double
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        double b = b1.subtract(b2).doubleValue();
        return b;
    }

    /**
     * 两个double型相加
     *
     * @param v1
     * @param v2
     * @return double
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        double b = b1.add(b2).doubleValue();
        return b;
    }

    /**
     * * 两个Double数相除 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    public static double div(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * * 两个Double数相除 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    public static double divRoundDown(double v1, double v2, int scale) {
        if (0d == v2) {
            return 0d;
        }
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_DOWN).doubleValue();
    }

    /**
     * * 两个BigDecimal数相除 *
     *
     * @param b1 *
     * @param b2 *
     * @return Double
     */
    public static double div(BigDecimal b1, BigDecimal b2) {
        return b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * * 两个BigDecimal数相除 *
     *
     * @param b1 *
     * @param b2 *
     * @return Double
     */
    public static double div(BigDecimal b1, BigDecimal b2, int scale) {
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    /**
     * 计算double类型除法,统一处理结果(四舍五入格式)
     * @param data1 被除数
     * @param data2 除数
     * @param newScale 小数位
     * @return 商(字符串类型)
     */
    public static Double divide(Double data1, Double data2, int newScale) {
        if(data1 == null || data2 == null){
            return 0d;
        }
        if (0d == data2.doubleValue()){
            return 0d;
        }
        return divideByType(data1, data2, newScale, BigDecimal.ROUND_DOWN);
    }

    /**
     * 计算double类型除法,且默认保留N位作为中间计算结果,统一处理结果(四舍五入格式)
     * @param data1 被除数
     * @param data2 除数
     * @return 商(字符串类型)
     */
    public static Double divideAsTemp(Double data1, Double data2){
        if(data1 == null || data2 == null){
            return 0d;
        }
        if (0d == data2.doubleValue()){
            return 0d;
        }
        return divideByType(data1, data2, FormatUtil.TEMP_VALUE_LENGTH, BigDecimal.ROUND_DOWN);
    }

    /**
     * 计算double类型除法,统一处理结果
     * @param data1 被除数
     * @param data2 除数
     * @param newScale 小数位
     * @param roundingMode 进位原则
     * @return 商(字符串类型)
     */
    public static Double divideByType(Double data1, Double data2, int newScale, int roundingMode){
        if (data1 == null || data2 == null){
            return 0d;
        }
        if (0d == data2.doubleValue()){
            return 0d;
        }
        BigDecimal b1 = new BigDecimal(String.valueOf(data1));
        BigDecimal b2 = new BigDecimal(String.valueOf(data2));
        return b1.divide(b2, newScale,roundingMode).doubleValue();
    }


    /**
     * 计算double类型除法,得到百分比结果(ROUND_DOWN)
     * @param data1 被除数
     * @param data2 除数
     * @param newScale 保留小数位数
     * @return 百分比结果
     */
    public static Double divideAsPercent(Double data1, Double data2, int newScale){
        return divideAsPercent(data1, data2, newScale, BigDecimal.ROUND_DOWN);
    }

    /**
     * 计算double类型除法,得到百分比结果(ROUND_DOWN)
     * @param data1 被除数
     * @param data2 除数
     * @param newScale 保留小数位数
     * @param roundingMode 进位原则
     * @return 百分比结果
     */
    public static Double divideAsPercent(Double data1, Double data2, int newScale, int roundingMode){
        if (data1 == null || data2 == null){
            return 0d;
        }
        if(0d == data2.doubleValue()){
            return 0d;
        }
        return multiply(divideByType(data1, data2, newScale+2, roundingMode), 100d, newScale);
    }

    /**
     * 提供精确的小数位只舍不入处理。
     *
     * @param v     需要只舍不入的数字
     * @param scale 小数点后保留几位
     * @return 只舍不入后的结果
     */
    public static double roundDown(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        return b.setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
    }

    /**
     * 提供精确的小数位只入不舍处理。
     *
     * @param v     需要只入不舍的数字
     * @param scale 小数点后保留几位
     * @return 只入不舍后的结果
     */
    public static double roundCeiling(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        return b.setScale(scale, BigDecimal.ROUND_CEILING).doubleValue();
    }

    /**
     * 将double型转String(非科学计数法)
     *
     * @param v 需要转换的数字
     * @return 转换结果
     */
    public static String douToStr(double v) {
        if (v >= 10000000 || v <= -10000000) {
            // 格式化输出
            DecimalFormat df = new DecimalFormat("#");
            return df.format(v);
        }
        return String.valueOf(v);
    }

    /**
     * 将BigDecimal转换成double
     * @param bigDecimal
     * @return
     */
    public static double big2Double(BigDecimal bigDecimal) {
        if (null != bigDecimal) {
            return bigDecimal.doubleValue();
        }
        return 0;
    }

    private static ThreadLocal<Integer> threadLocalScale = new ThreadLocal<>();
    public static void setResultScale(int scale){
        threadLocalScale.set(scale);
    }
    public static void clearResultScale(){
        threadLocalScale.remove();
    }

    /**
     * 计算double类型乘法,统一处理结果(ROUND_DOWN)
     * @param data1 乘数1
     * @param data2 乘数2
     * @param newScale 小数位
     * @return 乘积
     */
    public static Double multiply(Double data1, Double data2, int newScale){
        if (data1 == null || data2 == null){
            return null;
        }
        if (0d == data1.doubleValue() || 0d == data2.doubleValue()){
            return 0d;
        }
        return new BigDecimal(data1).multiply(new BigDecimal(data2)).setScale(newScale, BigDecimal.ROUND_DOWN).doubleValue();
    }

    /**
     * 安全的减法函数 注意:同为null时返回0
     * @param data1 数1
     * @param data2 数2
     * @return 差值
     */
    public static Double safeSub(Double data1, Double data2){
        if (data1 == null && data2 == null){
            return null;
        }
        if (data1 == null){
            return -data2;
        }
        if (data2 == null){
            return data1;
        }
        return new BigDecimal(data1).subtract(new BigDecimal(data2)).doubleValue();
    }

    /**
     * 安全的加法函数 注意:同为null时返回0
     * @param data1 数1
     * @param data2 数2
     * @return 和
     */
    public static Double safeAdd(Double data1, Double data2){
        if (data1 == null && data2 == null){
            return 0d;
        }
        if (data1 == null){
            return data2;
        }
        if (data2 == null){
            return data1;
        }
        return new BigDecimal(data1).add(new BigDecimal(data2)).doubleValue();
    }

    /**
     * 安全的加法函数 注意:同为null时返回null
     * @param data1 数1
     * @param data2 数2
     * @return 和
     */
    public static Double safeAddWithNull(Double data1, Double data2){
        if (data1 == null && data2 == null){
            return null;
        }
        if (data1 == null){
            return data2;
        }
        if (data2 == null){
            return data1;
        }
        return new BigDecimal(data1).add(new BigDecimal(data2)).doubleValue();
    }


    /**
     * 判断Double是否是空或者为0
     * @param number Double类型数
     * @return true:为null/等于0
     */
    public static boolean isEmpty(Double number) {
        if (number == null){
            return true;
        }
        if (number == 0d){
            return true;
        }
        return false;
    }


    public static int compareTo(Double num1, Double num2){
        if (num1 == null && num2 == null){
            return 0;
        }
        if (num1 == null){
            return -1;
        }
        if (num2 == null){
            return 1;
        }
        return num1.compareTo(num2);
    }




    /**
     * * 两个BigDecimal数相除 *
     *
     * @param b1 *
     * @param b2 *
     * @return Double
     */
    public static BigDecimal divBigDecimal(BigDecimal b1, BigDecimal b2) {
        if (b1 == null && b1 != null){
            return b2;
        } else if (b1 != null && b1 == null){
            return b1;
        } else if (b1 == null && b1 == null){
            return b1;
        } else {
            if (b2.intValue() == 0){
                return BigDecimal.ZERO;
            } else {
                return b1.divide(b2, TEMP_VALUE_LENGTH, BigDecimal.ROUND_HALF_UP);
            }
        }
    }
    public static Double divDouble(BigDecimal b1, BigDecimal b2) {
        if (b1 == null && b1 != null){
            return b2.doubleValue();
        } else if (b1 != null && b1 == null){
            return b1.doubleValue();
        } else if (b1 == null && b1 == null){
            return null;
        } else {
            if (b2.intValue() == 0){
                return 0.00;
            } else {
                return b1.divide(b2, TEMP_VALUE_LENGTH, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
        }
    }
    public static Integer divInteger(BigDecimal b1, BigDecimal b2) {
        if (b1 == null && b1 != null){
            return b2.intValue();
        } else if (b1 != null && b1 == null){
            return b1.intValue();
        } else if (b1 == null && b1 == null){
            return null;
        } else {
            if (b2.intValue() == 0){
                return 0;
            } else {
                return b1.divide(b2, TEMP_VALUE_LENGTH, BigDecimal.ROUND_HALF_UP).intValue();
            }
        }
    }
    /**
     * * 两个BigDecimal数加
     *
     * @param b1 *
     * @param b2 *
     * @return Double
     */
    public static BigDecimal addBigDecimal(BigDecimal b1, BigDecimal b2) {
        if (b1 == null && b1 != null){
            return b2;
        } else if (b1 != null && b1 == null){
            return b1;
        } else if (b1 == null && b1 == null){
            return b1;
        } else {
            return b1.add(b2);
        }
    }

    public static Double addDouble(BigDecimal b1, BigDecimal b2) {
        if (b1 == null && b1 != null){
            return b2.doubleValue();
        } else if (b1 != null && b1 == null){
            return b1.doubleValue();
        } else if (b1 == null && b1 == null){
            return null;
        } else {
            return b1.add(b2).doubleValue();
        }
    }

    public static Integer addInteger(Integer b1, Integer b2) {
        if (b1 == null && b1 != null){
            return b2;
        } else if (b1 != null && b1 == null){
            return b1;
        } else if (b1 == null && b1 == null){
            return null;
        } else {
            return b1 + b2;
        }
    }

    public static Boolean isEmpty(Object object){
        if(object==null || object==""){
            return true;
        }else {
            return false;
        }
    }

    public static String formattedDecimalToPercentage(BigDecimal decimal){
        //获取格式化对象
        NumberFormat nt = NumberFormat.getPercentInstance();
        //设置百分数精确度1即保留两位小数
        nt.setMinimumFractionDigits(1);
        return nt.format(decimal);
    }

    /**
     * Object与BigDecimal转换
     * @param value
     * @return
     */
    public static BigDecimal getBigDecimal(Object value) {
        BigDecimal bigDecimal = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                bigDecimal = (BigDecimal) value;
            } else if (value instanceof String) {
                bigDecimal = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                bigDecimal = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                bigDecimal = new BigDecimal(((Number) value).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
            }
        }
        return bigDecimal;
    }

    /**
     * 去除不可见字符，包含左右空格的都去除
     * 1.\n 回车(\u000a)
     * 2.\t 水平制表符(\u0009)
     * 3.\s 空格(\u0008)
     * 4.\r 换行(\u000d)
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        /*String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("").trim();
        }
        return dest;*/
        String dest = null;
        if (str != null) {
            return str.trim();
        }
        return dest;
    }

}
