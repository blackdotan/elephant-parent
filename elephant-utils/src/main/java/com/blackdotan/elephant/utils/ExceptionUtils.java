package com.blackdotan.elephant.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by wmw on 16/10/21.
 */
public class ExceptionUtils {
    /**
     * 异常类型 转成 字符串
     *
     * @param e 异常
     * @return 异常日志
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
