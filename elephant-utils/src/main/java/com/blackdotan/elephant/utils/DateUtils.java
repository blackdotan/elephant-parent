package com.blackdotan.elephant.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
     * @param pattern 时间格式
     * @return
     */
    public static String now(String pattern) {
        return new SimpleDateFormat(pattern).format(now());
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
     * @param date 时间
     * @param format 时间格式
     * @return 时间[字符型]
     */
    public static String dateToString(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * @param date/默认格式
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(date, STD_PATTERN);
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
     * @param text 时间[字符型]/默认格式
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String text) throws ParseException {
        return stringToDate(text, STD_PATTERN);
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
     * 日期拼接时间
     * @param date 日期
     * @param time 时间
     * @return
     */
    public static Date concat(Date date, Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Calendar dtime = Calendar.getInstance();
        dtime.setTime(time);

        calendar.set(Calendar.HOUR_OF_DAY, dtime.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, dtime.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, dtime.get(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * @param date
     * @return
     */
    public static Date daybegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * @param date
     * @return
     */
    public static Date dayend(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }


    /**
     * 时间转化
     * @param date　　时间
     * @param format　格式
     * @return
     * @throws ParseException
     */
    public static Date parser(Date date, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(dateToString(date, STD_PATTERN));
    }

    /**
     * @param date
     * @param begin
     * @param end
     * @return
     */
    public static boolean between(Date date, Date begin, Date end) {
        return (date.after(begin) || date.equals(begin)) && (date.before(end) || date.equals(end));
    }

    /**
     * @param date
     * @param begin
     * @param end
     * @param closed
     * @return
     */
    public static boolean between(Date date, Date begin, Date end, String closed) {
        return (date.after(begin) || (date.equals(begin) && "left".equals(closed))) && (date.before(end) || (date.equals(end) && "right".equals(closed)));
    }

    /**
     * 获得最近月份
     * @param limit
     * @return
     */
    public static List<Date> latest(int limit) {
        return latest(Calendar.DAY_OF_YEAR, limit);
    }

    /**
     * 获得最近月份
     * @param field
     * @param limit
     * @return
     */
    public static List<Date> latest(int field, int limit) {
        Date date = now();
        List<Date> arr = new ArrayList<>();
        for(int i=0; i < limit ; i++) {
            arr.add(DateUtils.dateWithOffset(date, -i, field));
        }
        return arr;
    }

    /**
     * 获得最近月份
     * @param pattern
     * @param field
     * @param limit
     * @return
     */
    public static List<String> latest(String pattern, int field, int limit) {
        Date date = now();
        List<Date> arr = new ArrayList<>();
        for(int i=0; i < limit ; i++) {
            arr.add(DateUtils.dateWithOffset(date, -i, field));
        }
        return arr.stream().map(e->dateToString(e, pattern)).collect(Collectors.toList());
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
