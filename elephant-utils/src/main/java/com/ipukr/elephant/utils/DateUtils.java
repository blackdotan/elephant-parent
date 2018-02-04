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


    /**
     * 获取当前时间
     * @return 时间
     */
    public static Date now(){
        return Calendar.getInstance(tz).getTime();
    }

    /**
     * 获取当前时间
     * @return 时间[字符型]
     */
    public static String nowStr(){
        return new SimpleDateFormat(STD_PATTERN).format(now());
    }

    /**
     * date类型转换为String类型
     *
     * @param data 时间
     * @param formatType 时间格式
     * @return 时间[字符型]
     */
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }


    /**
     * @param text 时间[字符型]
     * @param pattern 时间格式
     * @return 时间
     * @throws ParseException 格式转化异常
     */
    public static Date stringToDate(String text, String pattern) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.parse(text);
    }

    /**
     * 验证时间格式
     * @param text 时间[字符型]
     * @param pattern 时间格式
     * @return 是否正确
     */
    public static boolean validator(String text, String pattern){
        DateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(text) instanceof Date;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 计算时间偏移/偏移一天
     * @return 当前时间按天偏移-1
     */
    public static Date yesterday(){
        return dateWithOffset( Calendar.getInstance(tz).getTime(), -1, Calendar.DAY_OF_YEAR);
    }

    /**
     * 计算时间偏移/偏移一天
     * @return 当前时间按天偏移1
     */
    public static Date tomorrow(){
        return dateWithOffset( Calendar.getInstance(tz).getTime(), +1, Calendar.DAY_OF_YEAR);
    }

    /**
     * 计算时间偏移
     * @param offset 偏移量
     * @return 当前时间按天偏移结果
     */
    public static Date nowWithOffset(Integer offset){
        return dateWithOffset( Calendar.getInstance(tz).getTime(), offset, Calendar.DAY_OF_YEAR);
    }

    /**
     * 计算时间偏移
     * @param date 时间
     * @param offset 偏移量
     * @return 偏移后的时间
     */
    public static Date dateWithOffset(Date date, Integer offset){
        return dateWithOffset(date, offset, Calendar.DAY_OF_YEAR);
    }

    /**
     * 计算时间偏移
     * @param date 时间
     * @param offset 偏移量
     * @param flied 偏移量单位 Calendar.DAY/Calendar.YEAR ...
     * @return 偏移后的时间
     */
    public static Date dateWithOffset(Date date, Integer offset, int flied){
        Calendar calendar = Calendar.getInstance(tz);
        calendar.setTime(date);
        calendar.add(flied, offset);
        return calendar.getTime();
    }


    /**
     * 获取当前日期是星期几
     *
     * dt 时间
     * 当前日期是星期几
     */
    public static final String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * 根据时间，获取星期
     * @param date 时间
     * @return 中文星期
     */
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
     * @param date 时间
     * @return 0~6[星期日~星期六]
     */
    public static Integer getWeekOfDate(Date date) {
        int w = org.apache.commons.lang.time.DateUtils.toCalendar(date).get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return w;
    }
}
