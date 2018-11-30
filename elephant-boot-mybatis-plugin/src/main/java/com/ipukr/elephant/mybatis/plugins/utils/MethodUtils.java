package com.ipukr.elephant.mybatis.plugins.utils;

import com.ipukr.elephant.utils.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.internal.util.StringUtility;

import java.sql.JDBCType;

/**
 * Created by wmw on 1/16/17.
 */
public class MethodUtils {

    @Deprecated
    public static String annotation(String message){
        StringBuffer buffer = new StringBuffer();
        buffer  .append("/**\n")
                .append("\t *\n")
                .append("\t * {}\n".replace("{}", message))
                .append("\t *\n")
                .append("\t * This Method Was Generator By Pukr Mybatis-Plugin \n")
                .append("\t *\n")
                .append("\t * @version ${date} ${time}\n")
                .append("\t * */");
        return buffer.toString();
    }


    /**
     * 字段匹配XML
     *
     * <if test="record.type != null and record.type !=''" >
     *  and `Type` = #{record.type,jdbcType=VARCHAR,typeHandler=com.letransi.appint.entity.handler.SocialSharingTypeEnumTypeHandler},
     * </if>
     *
     * @param column
     * @return
     */
    public static XmlElement generateColumnEqXmlElement(IntrospectedColumn column, String prefix){

        String delimiterBegin = column.getContext().getBeginningDelimiter();
        String delimiterEnd = column.getContext().getEndingDelimiter();

        XmlElement condition = new XmlElement("if");
        if(column.getJdbcTypeName().equals(JDBCType.VARCHAR)){
            condition.addAttribute(new Attribute("test", prefix + column.getJavaProperty() + " != null and " + prefix + column.getJavaProperty() + " !=''"));
        } else {
            condition.addAttribute(new Attribute("test", prefix + column.getJavaProperty() + " != null"));
        }

        StringBuilder builder = new StringBuilder()
            .append(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(column))
            .append(" = ")
            .append(MyBatis3FormattingUtilities.getParameterClause(column, prefix));


        condition.addElement(new TextElement("and " + builder.toString()));
        return condition;
    }

    /**
     *
     *
     * @param column
     * @return
     */
    public static XmlElement generateColumnLikeXmlElement(IntrospectedColumn column, String prefix) {
        String delimiterBegin = column.getContext().getBeginningDelimiter();
        String delimiterEnd = column.getContext().getEndingDelimiter();

        XmlElement condition = new XmlElement("if");

        if(column.getJdbcTypeName().equals("VARCHAR")){
            condition.addAttribute(new Attribute("test", prefix + column.getJavaProperty() + " != null and " + prefix + column.getJavaProperty() + " !=''"));

            StringBuilder builder = new StringBuilder()
                    .append(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(column))
                    .append(" like concat('%', ")
                    .append(MyBatis3FormattingUtilities.getParameterClause(column, prefix))
                    .append(", '%')");

            condition.addElement(new TextElement("and " + builder.toString()));
        } else {
            condition.addAttribute(new Attribute("test", prefix + column.getJavaProperty() + " != null"));

            StringBuilder builder = new StringBuilder()
                    .append(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(column))
                    .append(" = ")
                    .append(MyBatis3FormattingUtilities.getParameterClause(column, prefix));

            condition.addElement(new TextElement("and " + builder.toString()));
        }


        return condition;
    }


    /**
     * @param column
     * @return
     */
    public static String generateCondition(IntrospectedColumn column) {
        return StringUtils.easyAppend(" and {}=#{{}, jdbcType={}}", column.getActualColumnName(), column.getJavaProperty(),column.getJdbcTypeName());
    }


    /**
     * @param topLevelClass
     * @param domain
     */
    public static void setMethod(TopLevelClass topLevelClass, String domain){
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);

        String setterName = StringUtils.easyAppend("set{}",domain);

        method.setName(setterName);

        method.addParameter(new Parameter(new FullyQualifiedJavaType(domain), ModelUtils.getDomainName(domain)));
        method.addBodyLine("this." + ModelUtils.getDomainName(domain) + "=" + ModelUtils.getDomainName(domain) + ";");
        topLevelClass.addMethod(method);

    }

    /**
     *
     * @param topLevelClass
     * @param domain
     */
    public static void getMethod(TopLevelClass topLevelClass, String domain) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType(domain));
        String getterName = StringUtils.easyAppend("get{}{}",
                domain.substring(0, 1).toUpperCase(),
                domain.substring(1));
        method.setName(getterName);

        method.addBodyLine("return this." + ModelUtils.getDomainName(domain) + ";");
        topLevelClass.addMethod(method);
    }

    /**
     * 生成Get方法
     * @param field
     * @return
     */
    public static Method generateGetMethod(Field field) {
        String getterName = StringUtils.easyAppend("get{}{}",
                field.getName().substring(0,1).toUpperCase(),
                field.getName().substring(1));

        Method getter = new Method(getterName);
        getter.setVisibility(JavaVisibility.PUBLIC);
        getter.setReturnType(field.getType());
        getter.addBodyLine("return this.{};".replace("{}", field.getName()));
        return getter;
    }

    /**
     * 生成Set方法
     * @param field
     * @return
     */
    public static Method generateSetMethod(Field field) {
        String setterName = StringUtils.easyAppend("set{}{}",
                field.getName().substring(0,1).toUpperCase(),
                field.getName().substring(1));

        Method setter = new Method(setterName);
        setter.setVisibility(JavaVisibility.PUBLIC);
        setter.addBodyLine("this.{} = {};".replace("{}", field.getName()));
        Parameter p1 = new Parameter(field.getType(), field.getName());
        setter.addParameter(p1);
        return setter;
    }
}
