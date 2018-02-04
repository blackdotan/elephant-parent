package com.ipukr.elephant.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by wmw on 16/10/21.
 */
public class DateUtils {

    public static String STD_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static TimeZone tz = TimeZone.getTimeZone("GMT+8");


    public static Date now(){
        return Calendar.getInstance(tz).getTime();
    }

    public static String nowStr(){
        return new SimpleDateFormat(STD_PATTERN).format(now());
    }

    // date类型转换为String类型
    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    // long类型转换为String类型
    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    // string类型转换为date类型
    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // long转换为Date类型
    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // string类型转换为long类型
    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    // date类型转换为long类型
    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public static boolean validator(String text, String pattern){
        DateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(text) instanceof Date;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * @return
     */
    public static Date yesterday(){
        Calendar calendar = Calendar.getInstance(tz);
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        return calendar.getTime();
    }

    /**
     * @return
     */
    public static Date tomorrow(){
        Calendar calendar = Calendar.getInstance(tz);
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, +1);
        return calendar.getTime();
    }

    public static Date nowWithOffset(Integer offset){
        Calendar calendar = Calendar.getInstance(tz);
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, offset);
        return calendar.getTime();
    }

    /**
     * @param date
     * @param offset
     * @return
     */
    public static Date dateWithOffset(Date date, Integer offset){
        Calendar calendar = Calendar.getInstance(tz);
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, offset);
        return calendar.getTime();
    }

    /**
     * @param date
     * @param offset
     * @return
     */
    public static Date dateWithOffset(Date date, Integer offset, int flied){
        Calendar calendar = Calendar.getInstance(tz);
        calendar.setTime(date);
        calendar.add(flied, offset);
        return calendar.getTime();
    }


    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static final String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    public static String getWeekOfDateToChinese(Date date) {

        int w = org.apache.commons.lang.time.DateUtils.toCalendar(date).get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取星期脚标 <br>
     *
     * 顺序如下:"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"
     *
     * @param date
     * @return
     */
    public static Integer getWeekOfDate(Date date) {
        int w = org.apache.commons.lang.time.DateUtils.toCalendar(date).get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return w;
    }
}
