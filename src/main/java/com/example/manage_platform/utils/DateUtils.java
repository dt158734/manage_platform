package com.example.manage_platform.utils;

import lombok.extern.log4j.Log4j2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//import org.joda.time.DateTime;
//import org.joda.time.Days;

@Log4j2
public class DateUtils {

    /**
     * 获取明天
     * @param today
     * @return
     */
    public static Date getTomorrowTime(Date today) {
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
        return c.getTime();
    }

    /**
     * 获取上一年
     * @param today
     * @return
     */
    public static Date getLastYear(Date today) {
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.YEAR, -1); // 年份减1
        //c.add(Calendar.MONTH, -1);// 月份减1
        //c.add(Calendar.DATE, -1);// 日期减1
        //c.add(Calendar.DAY_OF_MONTH, -1);// 今天+1天
        return c.getTime();
    }

    /**
     * 获取明年
     * @param today
     * @return
     */
    public static Date getNowYear(Date today) {
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.YEAR, 1);
        return c.getTime();
    }

    /**
     * 获取上一年,返回String
     * @param today
     * @return
     */
    public static String getLastYearStr(String today) {
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            c.setTime(sdf.parse(today));
            c.add(Calendar.YEAR, -1); // 年份减1
            return sdf.format(c.getTime());
        } catch (ParseException e) {

        }
        return null;
    }

    /**
     * 获取两个时间差多少天
     * @param today
     * @return
     */
    public static int getCalculatedays(String day, String today) {
        long between_days = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(today));
            long time1 = calendar.getTimeInMillis();
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(sdf.parse(day));
            long time2 = calendar1.getTimeInMillis();
            between_days = (time1 - time2) / (1000 * 3600 * 24) + 1;
        } catch (ParseException e) {

        }
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取当月最后一天
     *
     * @return
     */
    public static String getMonthLastDay() {
        Calendar a = Calendar.getInstance();
        //a.setTime(date);
        a.set(Calendar.YEAR, a.get(Calendar.YEAR));
        a.set(Calendar.MONTH, a.get(Calendar.MONTH));
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        int muu = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        String percent = String.valueOf((double) muu / (double) maxDate);
        return percent;
    }

    /**
     * 根据传入日期获取最后一天
     *
     * @param date
     * @return
     */
    public static String getMonthLastDay(Date date) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        int muu = a.get(Calendar.DAY_OF_MONTH);
        a.set(Calendar.YEAR, a.get(Calendar.YEAR));
        a.set(Calendar.MONTH, a.get(Calendar.MONTH));
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        String percent = String.valueOf((double) muu / (double) maxDate);
        return percent;
    }

    /**
     * 获取开始月
     * @param date
     * @return
     */
    public static String getStartMonthDay(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
            c.set(Calendar.DAY_OF_MONTH, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sdf.format(c.getTime());
    }

    /**
     * 获取结束月
     * @param date
     * @return
     */
    public static String getOldStratMonthDay(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
            c.set(Calendar.DAY_OF_MONTH, 1);
            c.add(Calendar.YEAR, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sdf.format(c.getTime());
    }

    /**
     * 获取当前日期
     * @param lo
     * @return
     */
    public static String longToDate(long lo){
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }

    /**
     * 比较两个日期的大小
     * @param beginTime
     * @param endTime
     * @return
     */
    public static int dateCompareTo(String beginTime, String endTime){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int compareTo = 0;
        try {
            Date date1 = format.parse(beginTime);
            Date date2 = format.parse(endTime);
            compareTo = date1.compareTo(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return compareTo;
    }

    /**
     * 日期类型转换
     * @param date
     * @return
     */
    public static Date getDate(String date) {
        Calendar c = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            c.setTime(sdf.parse(date));
            c.set(Calendar.DAY_OF_MONTH, 1);
            c.add(Calendar.YEAR, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }


    /**
     * 获取最后一天的月份
     * @param date
     * @return
     */
    public static String getLastDayofMonth(String date) {
        Calendar a = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            a.setTime(sdf.parse(date));
            a.set(Calendar.DAY_OF_MONTH, a.getActualMaximum(Calendar.DAY_OF_MONTH));
            a.add(Calendar.YEAR, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sdf.format(a.getTime());
    }


    /**
     * 获取小时
     * @param date
     * @return
     */
    public static String getHourPoint(String date) {
        Calendar c = Calendar.getInstance();
        int hour = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            c.setTime(sdf.parse(date));
            hour = c.get(Calendar.HOUR_OF_DAY);

        } catch (Exception e) {

        }
        return String.valueOf(hour);
    }

    /**
     * 校验闰年
     * @param thisYear
     * @return
     */
    public static Integer getReapyear(int thisYear) {
        if ((thisYear % 4 == 0 && thisYear % 100 != 0) || thisYear % 400 == 0) {
            return 0;
        }
        return 1;
    }

    /**
     * 获取某个月份的第一天的日期
     * @param dataDate
     * @return
     */
    public static String getFristAuthTime(String dataDate) {
        int year = Integer.parseInt(dataDate.substring(0, 4));
        int month = Integer.parseInt(dataDate.substring(5, 7));
        Calendar cale = null;
        cale = Calendar.getInstance();
        cale.set(Calendar.YEAR, year);
        cale.set(Calendar.MONTH, month-1);
        cale.set(Calendar.DAY_OF_MONTH,
                cale.getMinimum(Calendar.DATE));
        /*String date = new SimpleDateFormat("YYYY-MM-dd").format(cale.getTime());*/
        Date time = cale.getTime();
        Date date = new java.sql.Date(time.getTime());
        String s = date.toString();
        return s;
    }

    /**
     * 获取某个月份的最后一天的日期
     * @param dataDate
     * @return
     */
    public static String getLastAuthTime(String dataDate) {
        int year = Integer.parseInt(dataDate.substring(0, 4));
        int month = Integer.parseInt(dataDate.substring(5, 7));
        Calendar cale = null;
        cale = Calendar.getInstance();
        cale.set(Calendar.YEAR, year);
        cale.set(Calendar.MONTH, month-1);
        cale.set(Calendar.DATE, 1);//把日期设置为当月第一天
        cale.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = cale.get(Calendar.DATE);
        cale.set(Calendar.DAY_OF_MONTH,maxDate);
        String date = new SimpleDateFormat("YYYY-MM-dd").format(cale.getTime());
        return date;
    }

    /**
     * 获取今天是几月
     * @param date
     * @return
     */
    public static String getLastDayofTheMonth(String date) {
        Calendar a = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            a.setTime(sdf.parse(date));
            a.set(Calendar.DAY_OF_MONTH, a.getActualMaximum(Calendar.DAY_OF_MONTH));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sdf.format(a.getTime());
    }

    /**
     * 字符串转时间
     * @param dateStr
     * @return
     */
    public static long strToDateTime(String dateStr) {
        Date date = null;
        SimpleDateFormat strToDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = strToDate.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 在当前日期上减少一天
     * @param date
     * @return
     */
    public static String subtractDay(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String subtract = null;
        try {
            Date dd = df.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dd);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            subtract = df.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return subtract;
    }

    /**
     * 根据传入的date获取当月的参数
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 根据传入的时间String获取到当月有多少天
     * @param date
     * @return
     */
    public static int getDaysOfMonth(String date) {
        SimpleDateFormat sdf = null;
        if(date.length() == 7){
            sdf = new SimpleDateFormat("yyyy-MM");
        } else if(date.length() == 10){
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        Date parse = null;
        try {
            parse = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当年有多少天
     * @return
     */
    public static int getMaxDaysOfYear() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        int year = Integer.parseInt(format.substring(0, 4));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        int actualMaximum = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
        return actualMaximum;
    }

    /**
     * 获取当月的第一天
     * @return
     */
    public static long getMonthFirstDay() {
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String lastday = format.format(cale.getTime());
        Date date = new Date();
        try {
            date = format.parse(lastday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date.getTime();
    }

    /**
     * 根据当前传入的参数获取当前月的
     * @return
     */
    public static long getStringOrLongTime(String monthly) {
        Calendar cale = Calendar.getInstance();
        int year = Integer.parseInt(monthly.substring(0, 4));
        int month = Integer.parseInt(monthly.substring(5, 7));
        cale.set(year,month,0,0,0);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String lastday = format.format(cale.getTime());
        Date date = new Date();
        try {
            date = format.parse(lastday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date.getTime();
    }

    /**
     * 根据传入的时间yyyy-MM-dd获取当前时间的day返回
     * @param format
     * @return
     */
    public static int subDay(String format) {
        int day = Integer.parseInt(format.substring(8, 10));
        return day;
    }

    /**
     * 根据当前时间获取到当前月的日期
     * @return
     */
    public static int getDay() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        int day = Integer.parseInt(format.substring(8, 10));
        return day;
    }


    /**
     * 根据传入的时间yyyy-MM-dd获取当前时间的month返回
     * @param format
     * @return
     */
    public static int subMonth(String format) {
        int month = Integer.parseInt(format.substring(5, 7));
        return month;
    }

    /**
     * 根据当前时间获取到当前月的日期
     * @return
     */
    public static int getMonth() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        int month = Integer.parseInt(format.substring(5, 7));
        return month;
    }

    /**
     * 根据当前时间获取到当前年
     * @return
     */
    public static String getYear() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        String year = format.substring(0, 4);
        return year;
    }

    /**
     * 根据传入时间,在其基础上面增加一天
     * @param format
     * @return
     */
    public static String addOneDay(String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = sdf.parse(format);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(parse);
            // 把日期往后增加一天.整数往后推,负数往前移动
            calendar.add(calendar.DATE, 1);
            long timeInMillis = calendar.getTimeInMillis();
            Date date = new Date();
            date.setTime(timeInMillis);
            String result = sdf.format(date);
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据传入时间,在其基础上面增加day
     * @param format
     * @param day
     * @return
     */
    public static String addDay(String format, int day) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = sdf.parse(format);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(parse);
            // 把日期往后增加一天.整数往后推,负数往前移动
            calendar.add(Calendar.DATE, day);
            long timeInMillis = calendar.getTimeInMillis();
            Date date = new Date();
            date.setTime(timeInMillis);
            String result = sdf.format(date);
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据传入时间,在其基础上面减一天
     * @param format
     * @return
     */
    public static String subOneDay(String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = sdf.parse(format);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(parse);
            // 把日期往后增加一天.整数往后推,负数往前移动
            calendar.add(Calendar.DATE, -1);
            long timeInMillis = calendar.getTimeInMillis();
            Date date = new Date();
            date.setTime(timeInMillis);
            String result = sdf.format(date);
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据传入时间,在其基础上面减一天
     * @param format
     * @return
     */
    public static String subOneMonthly(String format) {
        String result = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(format !=null && !format .equals("")){
                SimpleDateFormat dataFornat = new SimpleDateFormat("yyyy-MM");
                Date parse = dataFornat.parse(format);
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(parse);
                // 把日期往后增加.整数往后推,负数往前移动
                calendar.add(calendar.MONTH, -1);
                long timeInMillis = calendar.getTimeInMillis();
                Date date = new Date();
                date.setTime(timeInMillis);
                result = sdf.format(date).substring(0,7);
            } else {
                if (getMonth() > 9){
                    return subOneMonthly(getYear() + "-" + getMonth());
                } else {
                    return subOneMonthly(getYear() + "-0" + getMonth());
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     * 根据传入时间,在其基础上面减一天
     * @param format
     * @return
     */
    public static String addOneMonthly(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            SimpleDateFormat dataFornat = new SimpleDateFormat("yyyy-MM");
            Date parse = dataFornat.parse(format);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(parse);
            // 把日期往后增加.整数往后推,负数往前移动
            calendar.add(calendar.MONTH, 1);
            long timeInMillis = calendar.getTimeInMillis();
            Date date = new Date();
            date.setTime(timeInMillis);
            String result = sdf.format(date).substring(0,7);
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算两个日期之间相差的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int daysBetween(String date1, String date2){
        if (date1.equals(date2)){
            return 1;
        }
        Date startTime = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            startTime = sdf.parse(date1);
            Date endTime = sdf.parse(date2);
            Calendar cal = Calendar.getInstance();
            cal.setTime(startTime);
            long time1 = cal.getTimeInMillis();
            cal.setTime(endTime);
            long time2 = cal.getTimeInMillis();
            long between_days=(time2-time1)/(1000*3600*24);
            return Integer.parseInt(String.valueOf(between_days)) + 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 返回年月日时分秒
     * @param date
     * @return
     */
    public static String datePrecisionProcessing(Date date){
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf4.format(date);
        return format;
    }

    /**
     * 返回转换后的日期
     * @param date
     * @return
     */
    public static String date(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        return format;
    }

    /**
     * 返回转换后的日期
     * @param date
     * @return
     */
    public static String datePrecisionProcessing(String date){
        Date startTime = null;
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            startTime = sdf4.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String format = sdf4.format(startTime);
        return format;
    }

    /**
     * 根据传入的当前时间!返回为拼接好的第一天的数据
     * @param year_month
     * @return
     */
    public static String mergeDate(String year_month) {
        String result = null;
        if (year_month.length() == 10){
            result = year_month.substring(0,7) + "-01";
        } else if (year_month.length() == 7){
            result = year_month + "-01";
        }
        return result;
    }

    /**
     * 比较时间进行合并
     * @param year_month
     * @param day
     * @return
     */
    public static String mergeDate(String year_month, int day) {
        String result = null;
        if (year_month.length() == 10){
            if (day >= 10){
                result = year_month.substring(0,7) + "-" + day;
            } else {
                result = year_month.substring(0,7) + "-0" + day;
            }
        } else if (year_month.length() == 7){
            if (day >= 10){
                result = year_month + "-" + day;
            } else {
                result = year_month + "-0" + day;
            }
        }
        return result;
    }

    /**
     * 比较时间进行拼接
     * @param year_month
     * @param day
     * @return
     */
    public static String mergeYearDate(String year_month, int day) {
        String result = null;
        if (day >= 10) {
            result = year_month.substring(0, 4) + "-" + day;
        } else {
            result = year_month.substring(0, 4) + "-0" + day;
        }
        return result;
    }

    /**
     * 获取当月最后一天
     * @param date
     * @return
     */
//    public static String getMonthEndDay(String date) {
//        String endMonthDay = null;
//        try {
//            DateTime dateTimeNow = new DateTime(date);
//            endMonthDay = dateTimeNow.plusDays(1-dateTimeNow.getDayOfMonth()).plusMonths(1).plusDays(-1).toString("yyyy-MM-dd");
//        } catch (Exception e) {
//            log.error("getMonthEndDay调用异常:",e);
//        }
//        return endMonthDay;
//    }

    /**
     * 获取当月开始填
     * @param startTime
     * @param endTime
     * @return
     */
//    public static int getDaysBetween(String startTime, String endTime){
//        int days = 0;
//        try{
//            DateTime start = new DateTime(startTime);
//            DateTime end = new DateTime(endTime);
//            days = Days.daysBetween(start, end).getDays();
//        }catch (Exception e){
//            log.error("getDaysBetween调用异常",e);
//        }
//        return days;
//    }

}

