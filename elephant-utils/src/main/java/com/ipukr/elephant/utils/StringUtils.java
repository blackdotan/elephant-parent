package com.ipukr.elephant.utils;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.util.UUID;

/**
 * Created by wmw on 16/10/21.
 */
public class StringUtils {
    public static String easyAppend(String text,Object arg){
        FormattingTuple ft = MessageFormatter.format(text, arg);
        return ft.getMessage();
    }

    public static String easyAppend(String text,Object arg1, Object arg2){
        FormattingTuple ft = MessageFormatter.format(text, arg1, arg2);
        return ft.getMessage();
    }

    public static String easyAppend(String text,Object...args){
        FormattingTuple ft = MessageFormatter.arrayFormat(text, args);
        return ft.getMessage();
    }


    public static String uuid(){
        return UUID.randomUUID().toString();
    }

}
