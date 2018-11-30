package com.ipukr.elephant.mybatis.plugins;

import com.ipukr.elephant.mybatis.plugins.utils.FieldUtils;
import com.ipukr.elephant.mybatis.plugins.utils.MethodUtils;
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
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

import java.sql.JDBCType;
import java.util.List;

/**
 * 全文搜索 / 追加属性
 *
 * Created by ryan on 上午3:23.
 */
public class FullsearchPlugin extends PluginAdapter {

    public static final String METHOD_NAME = "fullsearch";

    public static final String LABEL = "Fullsearch";

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


            iMethod.addParameter(new Parameter(new FullyQualifiedJavaType(List.class.getName()), "keys", "@Param(\"keys\")"));
            iMethod.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), "record", "@Param(\"record\")"));
            iMethod.addParameter(new Parameter(new FullyQualifiedJavaType("PageBounds"), "bounds"));
            iMethod.addAnnotation("/**");
            iMethod.addAnnotation(" * 全文搜索");
            iMethod.addAnnotation(" **/");
            iMethod.addAnnotation("@Override");
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



            //     <if test="keys!=null and keys.size>0">
            //      <foreach collection='keys' item='key'>
            //        AND CONCAT_WS(',', A.`Title`, B.`Name`, C.`Name`, D.`Name`) LIKE concat('%',#{key},'%')
            //      </foreach>
            //    </if>
            XmlElement condition = new XmlElement("if");
            condition.addAttribute(new Attribute("test", " keys != null and keys.size > 0"));
            XmlElement foreach = new XmlElement("foreach");
            foreach.addAttribute(new Attribute("collection", "keys"));
            foreach.addAttribute(new Attribute("item", "key"));

            StringBuilder builder = new StringBuilder("AND CONCAT_WS(',', ");

            for (IntrospectedColumn column : columns) {
                builder.append(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(column)).append(",");
            }
            builder.deleteCharAt(builder.length()-1);
            builder.append(") ").append("LIKE concat('%s',#{key},'%')");

            foreach.addElement(new TextElement(builder.toString()));
            condition.addElement(foreach);
            selectElement.addElement(condition);


            //
            for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
                selectElement.addElement(MethodUtils.generateColumnLikeXmlElement(column, "record."));
            }


            document.getRootElement().addElement(selectElement);
        }

        return true;

    }
}
