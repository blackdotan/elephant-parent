package com.ipukr.elephant.mybatis.plugins;

import com.ipukr.elephant.mybatis.plugins.utils.FieldUtils;
import com.ipukr.elephant.mybatis.plugins.utils.MethodUtils;
import com.ipukr.elephant.utils.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 属性拼接 / 追加属性
 *
 * Created by ryan on 上午3:23.
 */
public class UniquePlugin extends PluginAdapter {

    public static final String METHOD_NAME = "unique";

    public static final String LABEL = "Unique";

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {

        // 返回类型
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

        List<IntrospectedColumn> columns = FieldUtils.uniqueColumn(introspectedTable, LABEL);
        if( !columns.isEmpty() ) {

            // 方法1
            Method iMethod = new Method();

            iMethod.setName(METHOD_NAME);
            iMethod.setVisibility(method.getVisibility());
            iMethod.setReturnType(returnType);
            iMethod.getParameters().clear();

            for (IntrospectedColumn column : columns) {
                iMethod.addParameter(
                        new Parameter(column.getFullyQualifiedJavaType(), column.getJavaProperty(), "@Param(\"" + column.getJavaProperty() + "\")"));
            }
            iMethod.addAnnotation("/**");
            iMethod.addAnnotation(" * 根据唯一主键获取记录数据");
            iMethod.addAnnotation(" **/");
            interfaze.addMethod(iMethod);
        }

        return true;
    }


    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String iBaseColumnList = introspectedTable.getBaseColumnListId();
        String iTable =  introspectedTable.getTableConfiguration().getTableName();

        // 实体
        List<IntrospectedColumn> columns = FieldUtils.uniqueColumn(introspectedTable, LABEL);
        if ( columns.size() > 0 ) {
            XmlElement selectElement = new XmlElement("select");

            selectElement.addAttribute(new Attribute("id", METHOD_NAME));
            selectElement.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));
            selectElement.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));

            String sql = StringUtils.easyAppend("select <include refid='{}' /> from {} where 1=1 ", iBaseColumnList, iTable);
            selectElement.addElement(new TextElement(sql));

            StringBuilder builder = new StringBuilder();
            for (IntrospectedColumn column : columns) {
                builder.append(" AND ")
                        .append(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(column))
                        .append(" = ")
                        .append(MyBatis3FormattingUtilities.getParameterClause(column));

            }
            selectElement.addElement(new TextElement(builder.toString()));

            document.getRootElement().addElement(selectElement);
        }

        return true;

    }
}
