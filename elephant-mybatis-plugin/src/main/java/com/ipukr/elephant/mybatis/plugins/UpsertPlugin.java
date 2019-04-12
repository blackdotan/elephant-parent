package com.ipukr.elephant.mybatis.plugins;

import com.ipukr.elephant.mybatis.plugins.utils.MethodUtils;
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
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.GeneratedKey;

import java.util.Iterator;
import java.util.List;

/**
 * 插入 / 主键冲突忽略更新
 *
 * @author ryan
 *
 * Created by wmw on 4/27/16.
 */
public class UpsertPlugin extends PluginAdapter {

<<<<<<<<< Temporary merge branch 1
    public static final String UPSERT_METHOD_NAME = "upsert";
    public static final String BATUPSERT_METHOD_NAME = "batupsert";
=========
    public static final String METHOD_NAME = "upsert";
>>>>>>>>> Temporary merge branch 2

    /**
     * @param list
     * @return
     */
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
        Method iMethod = new Method();

        iMethod.setName(METHOD_NAME);
        iMethod.setVisibility(method.getVisibility());
        iMethod.setReturnType(FullyQualifiedJavaType.getIntInstance());
        iMethod.getParameters().clear();
        iMethod.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), "record", "@Param(\"record\")"));
        iMethod.addAnnotation("/**");
        iMethod.addAnnotation(" * 插入数据，主键冲突更新");
        iMethod.addAnnotation(" **/");
        interfaze.addMethod(iMethod);



        Method method2 = new Method();
        method2.setName(BATUPSERT_METHOD_NAME);
        method2.setVisibility(method.getVisibility());
        method2.setReturnType(new FullyQualifiedJavaType(Integer.class.getCanonicalName()));
        method2.getParameters().clear();

        FullyQualifiedJavaType p1 = new FullyQualifiedJavaType(List.class.getName());
        p1.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        method2.addParameter(new Parameter(p1, "records", "@Param(\"records\")"));
        method2.addAnnotation("/**");
        method2.addAnnotation(" * 批量插入数据，主键冲突更新");
        method2.addAnnotation(" **/");
        interfaze.addMethod(method2);

        return true;
    }



    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        genUpsertSqlMapDocumentGenerated(document, introspectedTable);
        genBatupsertSqlMapDocumentGenerated(document, introspectedTable);
        return true;
    }

    public void genUpsertSqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

        XmlElement insertElement = new XmlElement("insert");

        insertElement.addAttribute(new Attribute("id", UPSERT_METHOD_NAME));
        insertElement.addAttribute(new Attribute("parameterType", type.getFullyQualifiedName()));

        // Insert SQL 字段组装
        XmlElement keys = MyBatisUtilities.retConditionColumnElementList(introspectedTable, "record.", true);

        // Insert SQL Values组装
        XmlElement values = MyBatisUtilities.retConditionValueElementList(introspectedTable, "record.", true);

        StringBuffer update = new StringBuffer();

        for (IntrospectedColumn column : introspectedTable.getNonPrimaryKeyColumns()) {
            update
                    .append(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(column))
                    .append(" = values(")
                    .append(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(column))
                    .append("),");
        }
        // 去掉最后一个逗号分隔符
        if (update.length() > 2) {
            update.delete(update.length()-1, update.length());
        }

        MyBatisUtilities.fixGenerateKey(introspectedTable, insertElement);

        String sql = StringUtils.easyAppend("insert into {} ", tableName);
        insertElement.addElement(new TextElement(sql));
        insertElement.addElement(keys);
        insertElement.addElement(new TextElement(" VALUES "));
        insertElement.addElement(values);

        insertElement.addElement(new TextElement(" on duplicate key update " + update));

        document.getRootElement().addElement(insertElement);
    }

    public void genBatupsertSqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

        XmlElement insertElement = new XmlElement("insert");

        insertElement.addAttribute(new Attribute("id", BATUPSERT_METHOD_NAME));
        insertElement.addAttribute(new Attribute("parameterType", type.getFullyQualifiedName()));

        // Insert SQL 字段组装
        XmlElement keys = MyBatisUtilities.retConditionColumnElementList(introspectedTable, null, false);

        // Insert SQL Values组装
        XmlElement values = MyBatisUtilities.retConditionValueElementList(introspectedTable, "record.", false);

        XmlElement foreach = MyBatisUtilities.retForeachElement("records", "record", ",");
        foreach.addElement(values);

        StringBuffer update = new StringBuffer();

        for (IntrospectedColumn column :  introspectedTable.getNonPrimaryKeyColumns()) {
            update
                    .append(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(column))
                    .append(" = values(")
                    .append(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(column))
                    .append("),");
        }
        // 去掉最后一个逗号分隔符
        if (update.length() > 2) {
            update.delete(update.length()-1, update.length());
        }

        MyBatisUtilities.fixGenerateKey(introspectedTable, insertElement);

        String sql = StringUtils.easyAppend("insert into {} ", tableName);
        insertElement.addElement(new TextElement(sql));
        insertElement.addElement(keys);
        insertElement.addElement(new TextElement(" VALUE "));
        insertElement.addElement(foreach);
        insertElement.addElement(new TextElement(" on duplicate key update " + update));

        document.getRootElement().addElement(insertElement);
    }



}
