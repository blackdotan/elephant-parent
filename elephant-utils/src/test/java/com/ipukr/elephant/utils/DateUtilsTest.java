package com.ipukr.elephant.utils;

import org.junit.Test;

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
}
