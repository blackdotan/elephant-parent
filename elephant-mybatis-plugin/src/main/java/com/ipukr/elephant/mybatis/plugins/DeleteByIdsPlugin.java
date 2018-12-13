package com.ipukr.elephant.mybatis.plugins;

import com.ipukr.elephant.utils.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
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
 * 批量删除/只支持单主键
 * 
 * Created by wmw on 4/27/16.
 */
public class DeleteByIdsPlugin extends PluginAdapter {

    public static final String METHOD_NAME = "deleteByIds";

    @Override
    public boolean validate(List<String> list) {
        return true;
    }


    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        return super.contextGenerateAdditionalJavaFiles();
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        int size = introspectedTable.getPrimaryKeyColumns().size();
        if (size == 1) {
            Method iMethod = new Method();
            iMethod.setName(METHOD_NAME);
            iMethod.setVisibility(method.getVisibility());
            iMethod.setReturnType(FullyQualifiedJavaType.getIntInstance());
            iMethod.getParameters().clear();
            iMethod.addParameter(new Parameter(new FullyQualifiedJavaType(List.class.getName()), "records", "@Param(\"records\")"));
            iMethod.addAnnotation("/**");
            iMethod.addAnnotation(" * 根据主键数组，删除数据");
            iMethod.addAnnotation(" **/");
            interfaze.addMethod(iMethod);
        }

        return true;
    }



    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名
        String baseColumn = introspectedTable.getBaseColumnListId();


        if(introspectedTable.getPrimaryKeyColumns().size() == 1) {
            Iterator<IntrospectedColumn> it = introspectedTable.getPrimaryKeyColumns().iterator();

            XmlElement parentElement = document.getRootElement();
            XmlElement queryElement = new XmlElement("delete");
            queryElement.addAttribute(new Attribute("id", METHOD_NAME));

            String delimiterBegin = introspectedTable.getContext().getBeginningDelimiter();
            String delimiterEnd = introspectedTable.getContext().getEndingDelimiter();

            String condition = "";
            while (it.hasNext()) {
                IntrospectedColumn iIntrospectedColumn = it.next();

                condition = StringUtils.easyAppend(
                        "\t\n" +
                                "\t<choose>  \n" +
                                "\t <when test='records!=null and records.size>0'>  \n" +
                                "\t     and {} in  \n" +
                                "\t     <foreach collection='records' item='record' index='index' open='(' close=')' separator=','>  \n" +
                                "\t         #{record.{}}  \n" +
                                "\t     </foreach>  \n" +
                                "\t </when>  \n" +
                                "\t <otherwise>  \n" +
                                "\t     and 1 &lt;&gt; 1  \n" +
                                "\t </otherwise>  \n" +
                                "\t</choose>  \n", delimiterBegin + iIntrospectedColumn.getActualColumnName() + delimiterEnd, iIntrospectedColumn.getJavaProperty());

                if (it.hasNext()) {
                    throw new RuntimeException("Error not support multiple primary key, please use plugin with single primary key ");
                }
            }

            String text = StringUtils.easyAppend("delete from {} where 1=1 {}", tableName, condition);
            queryElement.addElement(new TextElement(text));
            parentElement.addElement(queryElement);
        }
        return true;
    }
}
