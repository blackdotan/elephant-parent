package com.ipukr.elephant.utils;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wmw on 16/10/21.
 */
public class StringUtils {
    /**
     * 字符拼接
     * @param text
     * @param arg
     * @return
     */
    public static String easyAppend(String text,Object arg){
        FormattingTuple ft = MessageFormatter.format(text, arg);
        return ft.getMessage();
    }

    /**
     * 字符拼接
     * @param text
     * @param arg1
     * @param arg2
     * @return
     */
    public static String easyAppend(String text,Object arg1, Object arg2){
        FormattingTuple ft = MessageFormatter.format(text, arg1, arg2);
        return ft.getMessage();
    }

    /**
     * 字符拼接
     * @param text
     * @param args
     * @return
     */
    public static String easyAppend(String text,Object...args){
        FormattingTuple ft = MessageFormatter.arrayFormat(text, args);
        return ft.getMessage();
    }

    /**
     * 随机UUID
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString();
    }

    /**
     * 字符串 隐藏 节选内容
     * @param number
     * @param i
     * @param j
     * @return
     */
    public static String hint(String number, int i, int j) {
        if (number != null && !number.equals("") && number.length() >= (i + j)) {
            String start = number.substring(0, i);
            String end = number.substring(number.length() - j, number.length());
            StringBuilder builder = new StringBuilder(start);
            for (int k = 0; k < number.length() - (i + j) ; k++) {
                builder.append("*");
            }
            builder.append(end);
            return builder.toString();
        }
        return number;
    }

    /**
     * 将字符串的首字母转大写
     * @param str 需要转换的字符串
     * @return
     */
    public static String toUpperWithFirstCharacter(String str) {
        // 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
        char[] cs = str.toCharArray();
        if ( cs[0] >= 97 && cs[0] <=122) {
            cs[0] -= 32;
            return String.valueOf(cs);
        } else {
            return str;
        }
    }

    /**
     * 将字符串的首字母转大写
     * @param str 需要转换的字符串
     * @return
     */
    public static String toLowerWithFirstCharacter(String str) {
        // 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
        char[] cs = str.toCharArray();
        if ( cs[0] >= 65 && cs[0] <= 90) {
            cs[0] -= 32;
            return String.valueOf(cs);
        } else {
            return str;
        }
    }

    /**
     *
     */
    public static Pattern numattern = Pattern.compile("[0-9]*");

    /**
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Matcher isNum = numattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
