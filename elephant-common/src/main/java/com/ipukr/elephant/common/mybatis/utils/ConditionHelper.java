package com.ipukr.elephant.common.mybatis.utils;

import com.ipukr.elephant.utils.DateUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/5/12.
 */
public class ConditionHelper {

    /**
     * @param objs
     * @return
     */
    public static String in(String col, List<? extends Serializable> objs) {
        StringBuffer condition = new StringBuffer(col).append(" IN(");
        for (Object obj : objs) {
            if (obj instanceof Number) {
                condition.append(obj).append(",");
            } else {
                condition.append("'").append(obj).append("'").append(",");
            }
        }
        if (objs.size() > 0) {
            condition.delete(condition.length() - 1, condition.length());
        }
        condition.append(")");
        return condition.toString();
    }

    /**
     * @param begin
     * @param end
     * @return
     */
    public static String between(String col, Date begin, Date end) {
        return between(col, begin, end, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @param begin
     * @param end
     * @param format
     * @return
     */
    public static String between(String col, Date begin, Date end, String format) {
        String beginstr = DateUtils.dateToString(begin, format);
        String endstr = DateUtils.dateToString(end, format);
        StringBuffer condition = new StringBuffer(col).append(" BETWEEN ");
        condition.append("'").append(beginstr).append("'")
                .append(" AND ")
                .append("'").append(endstr).append("'");
        return condition.toString();
    }


    /**
     * @param begin
     * @param end
     * @return
     */
    public static String between(String col, Integer begin, Integer end) {
        StringBuffer condition = new StringBuffer(col).append(" BETWEEN ");
        condition.append("'").append(begin).append("'")
                .append(" AND ")
                .append("'").append(end).append("'");
        return condition.toString();
    }
}
