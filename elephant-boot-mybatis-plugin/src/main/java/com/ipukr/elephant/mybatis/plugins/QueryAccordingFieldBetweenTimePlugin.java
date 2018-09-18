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
 * Created by wmw on 1/4/17.
 */
@Deprecated
public class QueryAccordingFieldBetweenTimePlugin extends PluginAdapter {
    public static final String METHOD_NAME = "queryAccordingFieldBetweenTime";


    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        Method iMethod = new Method();
        iMethod.setName(METHOD_NAME);
        iMethod.setVisibility(method.getVisibility());


        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(List.class.getName());
        returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        iMethod.setReturnType(returnType);

        iMethod.getParameters().clear();
        iMethod.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), "record", "@Param(\"record\")"));
        iMethod.addParameter(new Parameter(new FullyQualifiedJavaType("String"), "field", "@Param(\"field\")"));
        iMethod.addParameter(new Parameter(new FullyQualifiedJavaType("Date"), "start", "@Param(\"start\")"));
        iMethod.addParameter(new Parameter(new FullyQualifiedJavaType("Date"), "stop", "@Param(\"stop\")"));
        iMethod.addParameter(new Parameter(new FullyQualifiedJavaType("RowBounds"), "bounds"));

        interfaze.addMethod(iMethod);
        Set set = new HashSet<FullyQualifiedJavaType>();
        set.add(FullyQualifiedJavaType.getNewListInstance());
        set.add(new FullyQualifiedJavaType("java.util.Date"));
        set.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
        set.add(new FullyQualifiedJavaType("org.apache.ibatis.session.RowBounds"));
        interfaze.addImportedTypes(set);
        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名

        String baseColumn = introspectedTable.getBaseColumnListId();

        XmlElement parentElement = document.getRootElement();

        XmlElement query = new XmlElement("select");

        query.addAttribute(new Attribute("id", "queryWithTimeRange"));
        query.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));
        query.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        query.addElement(new TextElement("select <include refid=\"" + baseColumn + "\" /> from " + tableName + " where 1=1 and ${field} BETWEEN #{start,jdbcType=TIMESTAMP} AND #{stop,jdbcType=TIMESTAMP} "));

        for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
            query.addElement(MethodUtils.generateColumnEqXmlElement(column, "record."));
        }



        parentElement.addElement(query);

        return true;
    }

}
