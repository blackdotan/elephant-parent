package com.ipukr.elephant.mybatis.plugins;

import com.ipukr.elephant.mybatis.plugins.utils.MethodUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 记录统计
 * Created by wmw on 4/25/16.
 */
public class CountPlugin extends PluginAdapter {
    public static final String METHOD_NAME = "count";

    @Override
    public boolean validate(List<String> list) {
        return true;
    }


    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        // 引入包
        Set set = new HashSet<FullyQualifiedJavaType>();
        set.add(new FullyQualifiedJavaType(List.class.getName()));
        set.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
        set.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
        set.add(new FullyQualifiedJavaType("com.github.miemiedev.mybatis.paginator.domain.PageBounds"));
        interfaze.addImportedTypes(set);
        // 返回类型
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(Integer.class.getName());

        Method iMethod = new Method();

        iMethod.setName(METHOD_NAME);
        iMethod.setVisibility(method.getVisibility());
        iMethod.setReturnType(returnType);
        iMethod.getParameters().clear();
        iMethod.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), "record", "@Param(\"record\")"));
        iMethod.addAnnotation("/**");
        iMethod.addAnnotation(" * 统计匹配的记录数据");
        iMethod.addAnnotation(" **/");
        iMethod.addAnnotation("@Override");
        interfaze.addMethod(iMethod);

        return true;
    }


    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名

        String baseColumn = introspectedTable.getBaseColumnListId();

        XmlElement parentElement = document.getRootElement();

        XmlElement countElement = new XmlElement("select");

        countElement.addAttribute(new Attribute("id", METHOD_NAME));
        countElement.addAttribute(new Attribute("resultType", Integer.class.getName()));
        countElement.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        countElement.addElement(new TextElement("select count(1) from " + tableName + " where 1 = 1 "));


        String delimiterBegin = introspectedTable.getContext().getBeginningDelimiter();
        String delimiterEnd = introspectedTable.getContext().getEndingDelimiter();

        for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
            countElement.addElement(MethodUtils.generateColumnEqXmlElement(column, "record."));
        }

        parentElement.addElement(countElement);

        return true;
    }

}
