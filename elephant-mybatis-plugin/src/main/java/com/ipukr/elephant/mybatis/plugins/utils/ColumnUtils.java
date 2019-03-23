package com.ipukr.elephant.mybatis.plugins.utils;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.codegen.mybatis3.ListUtilities;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

import java.util.List;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/8/7.
 */
public class ColumnUtils {
    /**
     * 类名格式
     * @param column
     * @return
     */
    public static final String retClazzNameFormat(IntrospectedColumn column) {
        return column.getActualColumnName().replace("_", "");
    }

    /**
     * @param column
     * @return
     */
    public static final String retActualColumnName(IntrospectedColumn column) {
        return column.getActualColumnName();
    }


}
