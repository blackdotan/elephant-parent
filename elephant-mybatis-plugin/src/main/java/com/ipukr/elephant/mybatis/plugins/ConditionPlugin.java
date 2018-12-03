package com.ipukr.elephant.mybatis.plugins;

import com.ipukr.elephant.utils.StringUtils;
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
 * 条件查询
 *
 * Created by wmw on 1/6/17.
 */
public class ConditionPlugin extends PluginAdapter {

    private String name;

    public static final String METHOD_NAME = "condition";

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {

        // 引入类型
        Set set = new HashSet<FullyQualifiedJavaType>();
        set.add(new FullyQualifiedJavaType(List.class.getName()));
        set.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
        set.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
        set.add(new FullyQualifiedJavaType("org.apache.ibatis.session.RowBounds"));
        interfaze.addImportedTypes(set);
        // 返回类型
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(List.class.getName());
        returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));

        // 自定义方法
        Method iMethod = new Method();

        iMethod.setName(METHOD_NAME);
        iMethod.setVisibility(method.getVisibility());
        iMethod.setReturnType(returnType);
        iMethod.getParameters().clear();
        iMethod.addParameter(new Parameter(new FullyQualifiedJavaType("String"), "condition", "@Param(\"condition\")"));
        iMethod.addAnnotation("/**");
        iMethod.addAnnotation(" * 条件查询 eg: 1 = 1 ");
        iMethod.addAnnotation(" **/");
        iMethod.addAnnotation("@Override");
        interfaze.addMethod(iMethod);



        Method iMethod2 = new Method();

        iMethod2.setName(METHOD_NAME);
        iMethod2.setVisibility(method.getVisibility());
        iMethod2.setReturnType(returnType);

        iMethod2.getParameters().clear();
        iMethod2.addParameter(new Parameter(new FullyQualifiedJavaType("String"), "condition", "@Param(\"condition\")"));
        iMethod2.addParameter(new Parameter(new FullyQualifiedJavaType("RowBounds"), "bounds"));
        iMethod2.addAnnotation("/**");
        iMethod2.addAnnotation(" * 条件查询（分页）eg：1 = 1");
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

        XmlElement selectElement = new XmlElement("select");

        selectElement.addAttribute(new Attribute("id", METHOD_NAME));
        selectElement.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));

        String sql = StringUtils.easyAppend("select <include refid='{}' /> from {} where ${condition}",baseColumn,tableName);
        selectElement.addElement(new TextElement(sql));

        parentElement.addElement(selectElement);
        return true;
    }
}
