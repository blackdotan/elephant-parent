package com.ipukr.elephant.mybatis.plugins.utils;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/2/24.
 */
public class MyBatisUtilities {


    /**
     *
     * @param context
     * @return
     */
    public static String getMapperPackage(Context context) {
        String iTargetPackage = context.getJavaClientGeneratorConfiguration().getTargetPackage();
        return iTargetPackage;
    }

    public static String getModelPackage(Context context) {
        String iTargetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        return iTargetPackage;
    }


    /**
     * 获取字段名称
     * @param column
     * @return
     */
    public static final String retActualColumnName(IntrospectedColumn column) {
        return column.getActualColumnName();
    }


    /**
     * 获取字段对应类属性名
     * @param column
     * @return
     */
    public static final String retJavaProperty(IntrospectedColumn column) {
        return column.getJavaProperty();
    }

    /**
     * 获取字段对应类属性名，携带前缀
     * @param column
     * @param prefix
     * @return
     */
    public static final String retJavaProperty(IntrospectedColumn column, String prefix) {
        return column.getJavaProperty(prefix);
    }

    /**
     * @param column
     * @param prefix
     * @return
     */
    public static final Element retConditionColumnElement(IntrospectedColumn column, String prefix, boolean iftest){
        TextElement text = new TextElement(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(column) + ",");
        return iftest?iftest(column, prefix, text):text;
    }

    /**
     * @param column
     * @param prefix
     * @param element
     * @return
     */
    public static final XmlElement iftest(IntrospectedColumn column, String prefix, Element element) {
        XmlElement iftestele = new XmlElement("if");
        iftestele.addAttribute(new Attribute("test", (prefix!=null?prefix.trim():"") + column.getJavaProperty() + " != null"));
        iftestele.addElement(element);
        return iftestele;
    }

    /**
     * @param column
     * @param prefix
     * @param iftest
     * @return
     */
    public static final Element retConditionValueElement(IntrospectedColumn column, String prefix, boolean iftest) {
        TextElement text = new TextElement(MyBatis3FormattingUtilities.getParameterClause(column, prefix.trim()) + ",");
        return iftest?iftest(column, prefix, text):text;
    }

    /**
     * 返回
     * @param introspectedTable
     * @param prefix
     * @return
     */
    public static final XmlElement retConditionColumnElementList(IntrospectedTable introspectedTable, String prefix, boolean iftest){
        XmlElement keys = new XmlElement("trim");
        keys.addAttribute(new Attribute("prefix", "("));
        keys.addAttribute(new Attribute("suffix", ")"));
        keys.addAttribute(new Attribute("suffixOverrides", ","));
        for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
            keys.addElement(retConditionColumnElement(column, prefix, iftest));
        }
        return keys;
    }

    /**
     * 返回
     * @param introspectedTable
     * @param prefix
     * @return
     */
    public static final XmlElement retConditionValueElementList(IntrospectedTable introspectedTable, String prefix, boolean iftest) {
        XmlElement values = new XmlElement("trim");
        values.addAttribute(new Attribute("prefix", "("));
        values.addAttribute(new Attribute("suffix", ")"));
        values.addAttribute(new Attribute("suffixOverrides", ","));
        for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
            values.addElement(retConditionValueElement(column, prefix, iftest));
        }
        return values;
    }

    /**
     * 生成 foreach 标签
     * @param collection
     * @param item
     * @return
     */
    public static final XmlElement retForeachElement(String collection, String item, String separator) {
        XmlElement element = new XmlElement("foreach");
        element.addAttribute(new Attribute("collection", collection));
        element.addAttribute(new Attribute("item", item));
        element.addAttribute(new Attribute("index", "index"));
        element.addAttribute(new Attribute("separator", separator));
        return element;
    }

    /**
     * 生成 foreach 标签
     * @param collection
     * @param item
     * @return
     */
    public static final XmlElement retForeachElement(String collection, String item, String open, String close, String separator) {
        XmlElement element = new XmlElement("foreach");
        element.addAttribute(new Attribute("collection", collection));
        element.addAttribute(new Attribute("item", item));
        element.addAttribute(new Attribute("index", "index"));
        element.addAttribute(new Attribute("open", open));
        element.addAttribute(new Attribute("close", close));
        element.addAttribute(new Attribute("separator", separator));
        return element;
    }

    /**
     * FIX 自增
     * @param introspectedTable
     * @param element
     */
    public static final void fixGenerateKey(IntrospectedTable introspectedTable, XmlElement element) {
        GeneratedKey gk = introspectedTable.getGeneratedKey();
        if (gk != null) {
            IntrospectedColumn introspectedColumn = introspectedTable.getColumn(gk.getColumn());
            if (introspectedColumn != null) {
                String identityColumnType = introspectedColumn.getFullyQualifiedJavaType().getFullyQualifiedName();

                if (gk.isJdbcStandard()) {
                    element.addAttribute(new Attribute("useGeneratedKeys", "true"));
                    element.addAttribute(new Attribute("keyProperty", introspectedColumn.getJavaProperty()));
                } else {
                    XmlElement answer = new XmlElement("selectKey");
                    answer.addAttribute(new Attribute("resultType", identityColumnType));
                    answer.addAttribute(new Attribute("keyProperty", introspectedColumn.getJavaProperty()));
                    answer.addAttribute(new Attribute("order", gk.getMyBatis3Order()));
                    answer.addElement(new TextElement(gk.getRuntimeSqlStatement()));
                    element.addElement(answer);
                }
            }
        }
    }
}
