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

import java.util.Iterator;
import java.util.List;

/**
 * Created by wmw on 16/9/25.
 */
@Deprecated
public class FindPlugin extends PluginAdapter {

    public static final String METHOD_NAME = "findByIds";

    @Override
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
        selectElement.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));
        selectElement.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));

        Iterator<IntrospectedColumn> it = introspectedTable.getPrimaryKeyColumns().iterator();

        String condition = "";
        if(it.hasNext()){
            IntrospectedColumn iIntrospectedColumn = it.next();
            condition = StringUtils.easyAppend("{} = #{record.{}}", iIntrospectedColumn.getActualColumnName(), iIntrospectedColumn.getJavaProperty());
            if(it.hasNext()){
                throw new RuntimeException("Error not support multiple primary key, please use plugin with primary key (integer)");
            }
        }

        String sql = StringUtils.easyAppend("select <include refid='{}' /> from {} where 1=1 {}" ,baseColumn, tableName, condition);
        selectElement.addElement(new TextElement(sql));

        parentElement.addElement(selectElement);

        return true;
    }
}
