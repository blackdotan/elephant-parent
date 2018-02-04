package com.ipukr.elephant.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by wmw on 16/10/21.
 */
public class ExceptionUtils {
    /**
     * 异常类型 转成 字符串
     * */
    public static String castEx2Str(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }
}
