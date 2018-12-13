package com.ipukr.elephant.mybatis.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 记录抽样类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/14.
 */
public class ExamplePlugin extends PluginAdapter {

    public static final String METHOD_NAME1 = "example";

    public static final String METHOD_NAME2 = "nexample";

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {

        Method iMethod1 = new Method();

        iMethod1.setName(METHOD_NAME1);
        iMethod1.setVisibility(method.getVisibility());
        iMethod1.setReturnType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        iMethod1.getParameters().clear();
        iMethod1.addAnnotation("/**");
        iMethod1.addAnnotation(" * 随机一条数据");
        iMethod1.addAnnotation(" **/");
        interfaze.addMethod(iMethod1);


        // 引入包
        Set set = new HashSet<FullyQualifiedJavaType>();
        set.add(new FullyQualifiedJavaType(List.class.getName()));
        set.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
        set.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));

        interfaze.addImportedTypes(set);
        // 返回类型
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(List.class.getName());
        returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        Method iMethod2 = new Method();
        iMethod2.setName(METHOD_NAME2);
        iMethod2.setVisibility(method.getVisibility());
        iMethod2.setReturnType(returnType);
        iMethod2.getParameters().clear();
        iMethod2.addAnnotation("/**");
        iMethod2.addAnnotation(" * 随机n条数据");
        iMethod2.addAnnotation(" **/");
        interfaze.addMethod(iMethod2);

        return true;
    }



    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名

        String baseColumn = introspectedTable.getBaseColumnListId();

        XmlElement parentElement = document.getRootElement();

        XmlElement queryElement1 = new XmlElement("select");
        queryElement1.addAttribute(new Attribute("id", METHOD_NAME1));
        queryElement1.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));
        queryElement1.addElement(new TextElement("select <include refid=\"" + baseColumn + "\" /> from " + tableName + " where 1=1 order by RAND() limit 1"));

        parentElement.addElement(queryElement1);

        XmlElement queryElement2 = new XmlElement("select");
        queryElement2.addAttribute(new Attribute("id", METHOD_NAME2));
        queryElement2.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));
        queryElement2.addElement(new TextElement("select <include refid=\"" + baseColumn + "\" /> from " + tableName + " where 1=1 order by RAND() limit #{n}"));

        parentElement.addElement(queryElement2);

        return true;
    }
}
