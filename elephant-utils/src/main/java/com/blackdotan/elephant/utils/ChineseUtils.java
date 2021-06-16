package com.blackdotan.elephant.utils;

import java.io.UnsupportedEncodingException;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/2/8.
 */
public class ChineseUtils {

    public static String big5ToChinese( String text ) throws UnsupportedEncodingException {
        if ( text == null || text.equals( "" ) ) {
            return ("");
        } else {
            return new String(text.getBytes("big5"), "gb2312");
        }
    }


    public static String ChineseTobig5(String text ) throws UnsupportedEncodingException {
        if ( text == null || text.equals( "" ) ) {
            return ("");
        } else {
            return new String(text.getBytes("gb2312"), "big5");
        }
    }
}
