package com.ipukr.elephant.mybatis.plugins;

import com.ipukr.elephant.mybatis.plugins.utils.MethodUtils;
import com.ipukr.elephant.mybatis.plugins.utils.MyBatisUtilities;
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
 * 插入 / 主键冲突忽略插入
 *
 * @author ryan
 *
 * Created by wmw on 4/27/16.
 */
public class IgsertPlugin extends PluginAdapter {

    public static final String IGSERT_METHOD_NAME = "igsert";

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
        Method method1 = new Method();

        method1.setName(IGSERT_METHOD_NAME);
        method1.setVisibility(method.getVisibility());
        method1.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method1.getParameters().clear();
        method1.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), "record", "@Param(\"record\")"));
        method1.addAnnotation("/**");
        method1.addAnnotation(" * 插入数据，主键冲突忽略修改");
        method1.addAnnotation(" **/");
        interfaze.addMethod(method1);

        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        getnIgsertSqlMapDocumentGenerated(document, introspectedTable);
        return true;
    }


    public void getnIgsertSqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

        XmlElement insertElement = new XmlElement("insert");

        insertElement.addAttribute(new Attribute("id", IGSERT_METHOD_NAME));
        insertElement.addAttribute(new Attribute("parameterType", type.getFullyQualifiedName()));

        // Insert SQL 字段组装
        XmlElement keys = MyBatisUtilities.retConditionColumnElementList(introspectedTable, "record.", true);

        // Insert SQL Values组装
        XmlElement values = MyBatisUtilities.retConditionValueElementList(introspectedTable, "record.", true);

        // FIX Generate Key
        MyBatisUtilities.fixGenerateKey(introspectedTable, insertElement);

        String sql = StringUtils.easyAppend("insert ignore into {} ", tableName);
        insertElement.addElement(new TextElement(sql));
        insertElement.addElement(keys);
        insertElement.addElement(new TextElement(" VALUES "));
        insertElement.addElement(values);

        document.getRootElement().addElement(insertElement);
    }







}
