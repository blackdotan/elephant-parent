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
 * 匹配查询
 *
 * Created by wmw on 4/25/16.
 */
public class QueryPlugin extends PluginAdapter {
    public static final String METHOD_NAME = "query";

    /**
     * @param list
     * @return
     */
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
        set.add(new FullyQualifiedJavaType("org.apache.ibatis.session.RowBounds"));
        interfaze.addImportedTypes(set);
        // 返回类型
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(List.class.getName());
        returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));

        //
        Method iMethod = new Method();

        iMethod.setName(METHOD_NAME);
        iMethod.setVisibility(method.getVisibility());
        iMethod.setReturnType(returnType);

        iMethod.getParameters().clear();
        iMethod.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), "record", "@Param(\"record\")"));
        iMethod.addAnnotation("/**");
        iMethod.addAnnotation(" * 匹配记录");
        iMethod.addAnnotation(" **/");
        iMethod.addAnnotation("@Override");
        interfaze.addMethod(iMethod);

        Method iMethod2 = new Method();

        iMethod2.setName(METHOD_NAME);
        iMethod2.setVisibility(method.getVisibility());
        iMethod2.setReturnType(returnType);

        iMethod2.getParameters().clear();
        iMethod2.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), "record", "@Param(\"record\")"));
        iMethod2.addParameter(new Parameter(new FullyQualifiedJavaType("RowBounds"), "bounds"));
        iMethod2.addAnnotation("/**");
        iMethod2.addAnnotation(" * 匹配记录（分页）");
        iMethod2.addAnnotation(" **/");
        iMethod2.addAnnotation("@Override");
        interfaze.addMethod(iMethod2);


        return true;
    }


    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名

        String baseColumn = introspectedTable.getBaseColumnListId();

        XmlElement parentElement = document.getRootElement();

        XmlElement queryElement = new XmlElement("select");

        queryElement.addAttribute(new Attribute("id", METHOD_NAME));
        queryElement.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));
        queryElement.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        queryElement.addElement(new TextElement("select <include refid=\"" + baseColumn + "\" /> from " + tableName + " where 1=1 "));


        String delimiterBegin = introspectedTable.getContext().getBeginningDelimiter();
        String delimiterEnd = introspectedTable.getContext().getEndingDelimiter();

        for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
            queryElement.addElement(MethodUtils.generateColumnEqXmlElement(column, "record.") );
        }

        parentElement.addElement(queryElement);

        return true;
    }

}