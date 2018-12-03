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
 * SQL语句执行
 *
 * Created by ryan on 上午3:32.
 */
@Deprecated
public class SqlPlugin extends PluginAdapter {

    public static final String METHOD_NAME = "sql";

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        // 引入类型
        Set set = new HashSet<FullyQualifiedJavaType>();
        set.add(new FullyQualifiedJavaType(List.class.getName()));
        set.add(new FullyQualifiedJavaType("java.util.Map"));
        set.add(new FullyQualifiedJavaType("java.util.List"));
        interfaze.addImportedTypes(set);
        // 返回类型
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(List.class.getName());
        returnType.addTypeArgument(new FullyQualifiedJavaType("java.util.Map"));

        // 自定义方法
        Method iMethod = new Method();

        iMethod.setName(METHOD_NAME);
        iMethod.setVisibility(method.getVisibility());
        iMethod.setReturnType(returnType);

        iMethod.getParameters().clear();
        iMethod.addParameter(new Parameter(new FullyQualifiedJavaType("String"), "sql", "@Param(\"sql\")"));
        interfaze.addMethod(iMethod);

        return true;
    }


    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名

        String baseColumn = introspectedTable.getBaseColumnListId();

        XmlElement parentElement = document.getRootElement();

        XmlElement selectElement = new XmlElement("select");

        selectElement.addAttribute(new Attribute("id", METHOD_NAME));
        selectElement.addAttribute(new Attribute("resultType", "java.util.HashMap"));

        String sql = StringUtils.easyAppend("${sql}");
        selectElement.addElement(new TextElement(sql));

        parentElement.addElement(selectElement);
        return true;
    }
}
