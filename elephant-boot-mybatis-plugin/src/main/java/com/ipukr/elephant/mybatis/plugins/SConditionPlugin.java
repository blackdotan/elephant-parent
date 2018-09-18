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
 * 模糊匹配 + 条件查询
 *
 * Created by wmw on 4/27/16.
 */
public class SConditionPlugin extends PluginAdapter {

    public static final String METHOD_NAME = "scondition";

    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        // 引入包
        Set set = new HashSet<FullyQualifiedJavaType>();
        set.add(FullyQualifiedJavaType.getNewListInstance());
        set.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
        interfaze.addImportedTypes(set);
        // 返回类型
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(List.class.getName());
        returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));

        // 自定义方法
        Method iMethod3 = new Method();

        iMethod3.setName(METHOD_NAME);
        iMethod3.setVisibility(method.getVisibility());
        iMethod3.setReturnType(returnType);

        iMethod3.getParameters().clear();
        iMethod3.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), "record", "@Param(\"record\")"));
        iMethod3.addParameter(new Parameter(new FullyQualifiedJavaType(String.class.getName()), "condition", "@Param(\"condition\")"));
        iMethod3.addAnnotation("/**");
        iMethod3.addAnnotation(" * 模糊匹配 + 条件，获取数据");
        iMethod3.addAnnotation(" **/");
        iMethod3.addAnnotation("@Override");
        interfaze.addMethod(iMethod3);

        Method iMethod4 = new Method();

        iMethod4.setName(METHOD_NAME);
        iMethod4.setVisibility(method.getVisibility());
        iMethod4.setReturnType(returnType);

        iMethod4.getParameters().clear();
        iMethod4.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), "record", "@Param(\"record\")"));
        iMethod4.addParameter(new Parameter(new FullyQualifiedJavaType(String.class.getName()), "condition", "@Param(\"condition\")"));
        iMethod4.addParameter(new Parameter(new FullyQualifiedJavaType("PageBounds"), "bounds"));
        iMethod4.addAnnotation("/**");
        iMethod4.addAnnotation(" * 模糊匹配 + 条件，获取数据（分页）");
        iMethod4.addAnnotation(" **/");
        iMethod4.addAnnotation("@Override");
        interfaze.addMethod(iMethod4);


        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名
        String baseColumn = introspectedTable.getBaseColumnListId();

        XmlElement parentElement = document.getRootElement();

        //创建Query语句
        XmlElement queryElement = new XmlElement("select");

        queryElement.addAttribute(new Attribute("id", METHOD_NAME));
        queryElement.addAttribute(new Attribute("resultMap",introspectedTable.getBaseResultMapId()));
        queryElement.addAttribute(new Attribute("parameterType",introspectedTable.getBaseRecordType()));
        queryElement.addElement(new TextElement("select <include refid=\""+baseColumn+"\" /> from "+ tableName +" where 1=1 "));

        // 支持condition
        XmlElement condition = new XmlElement("if");
        condition.addAttribute(new Attribute("test", "condition != null"));
        condition.addElement(new TextElement("and ${condition} "));
        queryElement.addElement(condition);


        for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
            queryElement.addElement(MethodUtils.generateColumnLikeXmlElement(column, "record."));
        }
        parentElement.addElement(queryElement);

        return true;
    }
}
