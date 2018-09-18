package com.ipukr.elephant.mybatis.plugins;

import com.ipukr.elephant.utils.StringUtils;
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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by wmw on 16/9/25.
 */
@Deprecated
public class FindAccordingFieldConditionParamsPlugin extends PluginAdapter {

    public static final String METHOD_NAME = "findAccordingFieldConditionParams";

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        // 引入包
        Set set = new HashSet<FullyQualifiedJavaType>();
        set.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
        set.add(new FullyQualifiedJavaType("com.github.miemiedev.mybatis.paginator.domain.PageBounds"));
        set.add(new FullyQualifiedJavaType(Set.class.getName()));
        interfaze.addImportedTypes(set);
        // 返回类型
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(List.class.getName());
        returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));

        // 非分页方法
        Method iMethod = new Method();

        iMethod.setName(METHOD_NAME);
        iMethod.setVisibility(method.getVisibility());

        iMethod.setReturnType(returnType);

        iMethod.getParameters().clear();
        iMethod.addParameter(new Parameter(new FullyQualifiedJavaType("String"), "field", "@Param(\"field\")"));
        iMethod.addParameter(new Parameter(new FullyQualifiedJavaType("String"), "condition", "@Param(\"condition\")"));
        iMethod.addParameter(new Parameter(new FullyQualifiedJavaType("Object"), "params", "@Param(\"params\")"));
        interfaze.addMethod(iMethod);

        // 分页方法
        Method iMethod2 = new Method();

        iMethod2.setName(METHOD_NAME);
        iMethod2.setVisibility(method.getVisibility());

        iMethod2.setReturnType(returnType);

        iMethod2.getParameters().clear();
        iMethod2.addParameter(new Parameter(new FullyQualifiedJavaType("String"), "field", "@Param(\"field\")"));
        iMethod2.addParameter(new Parameter(new FullyQualifiedJavaType("String"), "condition", "@Param(\"condition\")"));
        iMethod2.addParameter(new Parameter(new FullyQualifiedJavaType("Object"), "param", "@Param(\"param\")"));
        iMethod2.addParameter(new Parameter(new FullyQualifiedJavaType("PageBounds"), "bounds"));
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
        selectElement.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));



        Iterator<IntrospectedColumn> it = introspectedTable.getPrimaryKeyColumns().iterator();

        String condition = StringUtils.easyAppend(" ${field} ${condition} #{param} ");

        String sql = StringUtils.easyAppend("select <include refid='{}' /> from {} where 1=1 and {}", baseColumn, tableName, condition);
        selectElement.addElement(new TextElement(sql));

        parentElement.addElement(selectElement);

        return true;
    }
}
