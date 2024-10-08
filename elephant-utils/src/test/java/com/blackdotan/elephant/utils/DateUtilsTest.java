package com.blackdotan.elephant.utils;

import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/9.
 */
public class DateUtilsTest {
    @Test
    public void testName() throws Exception {
        System.out.println(DateUtils.getWeekOfDateToChinese(DateUtils.nowWithOffset(+4)));
        System.out.println(DateUtils.getWeekOfDateToChinese(DateUtils.nowWithOffset(+3)));
        System.out.println(DateUtils.getWeekOfDateToChinese(DateUtils.nowWithOffset(+2)));
        System.out.println(DateUtils.getWeekOfDateToChinese(DateUtils.nowWithOffset(+1)));
        System.out.println(DateUtils.getWeekOfDateToChinese(DateUtils.now()));
        System.out.println(DateUtils.getWeekOfDateToChinese(DateUtils.nowWithOffset(-1)));
        System.out.println(DateUtils.getWeekOfDateToChinese(DateUtils.nowWithOffset(-2)));
        System.out.println(DateUtils.getWeekOfDateToChinese(DateUtils.nowWithOffset(-3)));
        System.out.println(DateUtils.getWeekOfDateToChinese(DateUtils.nowWithOffset(-4)));
        System.out.println(DateUtils.getWeekOfDateToChinese(DateUtils.nowWithOffset(-5)));
        System.out.println(DateUtils.getWeekOfDateToChinese(DateUtils.nowWithOffset(-6)));
        System.out.println(DateUtils.getWeekOfDateToChinese(DateUtils.nowWithOffset(-7)));
        System.out.println(DateUtils.getWeekOfDateToChinese(DateUtils.nowWithOffset(-8)));
        System.out.println(DateUtils.getWeekOfDateToChinese(DateUtils.nowWithOffset(-9)));
        System.out.println(DateUtils.getWeekOfDateToChinese(DateUtils.nowWithOffset(-10)));
    }

    @Test
    public void between() throws ParseException {
        Date date = DateUtils.stringToDate("2018-09-01 11:00:00", DateUtils.STD_PATTERN);
        Date begin = DateUtils.stringToDate("2018-09-01 10:00:00", DateUtils.STD_PATTERN);
        Date end = DateUtils.stringToDate("2018-09-01 11:00:00", DateUtils.STD_PATTERN);
        System.out.println(DateUtils.between(date, begin, end, null));
    }

    @Test
    public void latest() {
        for (Date item : DateUtils.latest(12)) {
            System.out.println(item);
        }
        for (String item : DateUtils.latest("yyyyMM", Calendar.MONTH,12)) {
            System.out.println(item);
        }
    }

    @Test
    public void daybeginend() throws ParseException {
        Date date = DateUtils.stringToDate("2018-09-01 11:00:00", DateUtils.STD_PATTERN);
        System.out.println(DateUtils.daybegin(date));
        System.out.println(DateUtils.dayend(date));
    }

    @Test
    public void name() {
        Date date = DateUtils.now();
        {
            Date begin = DateUtils.obtainMonthBeginOfDate(date);
            Date end = DateUtils.obtainMonthEndOfDate(date);
            System.out.println(begin);
            System.out.println(end);
        }
//        {
//            Pair<Date, Date> pair = DateUtils.obtainMonthRangeOfDate(date);
//            Date begin = pair.getKey();
//            Date end = pair.getValue();
//            System.out.println(begin);
//            System.out.println(end);
//        }

    }
}
