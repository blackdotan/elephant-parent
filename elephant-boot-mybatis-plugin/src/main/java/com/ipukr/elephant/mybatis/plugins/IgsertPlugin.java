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
 * 插入 / 主键冲突忽略插入
 *
 * @author ryan
 *
 * Created by wmw on 4/27/16.
 */
public class IgsertPlugin extends PluginAdapter{

    public static final String METHOD_NAME = "igsert";

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
        iMethod.addAnnotation(" * 插入数据，主键冲突忽略修改");
        iMethod.addAnnotation(" **/");
        iMethod.addAnnotation("@Override");
        interfaze.addMethod(iMethod);

        return true;
    }



    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名
        String baseColumn = introspectedTable.getBaseColumnListId();
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());


        Iterator<IntrospectedColumn> it = introspectedTable.getPrimaryKeyColumns().iterator();

        XmlElement parentElement = document.getRootElement();
        XmlElement insertElement = new XmlElement("insert");

        insertElement.addAttribute(new Attribute("id", METHOD_NAME));
        insertElement.addAttribute(new Attribute("parameterType", type.getFullyQualifiedName()));


        String delimiterBegin = introspectedTable.getContext().getBeginningDelimiter();
        String delimiterEnd = introspectedTable.getContext().getEndingDelimiter();


        // Insert SQL 字段组装
        XmlElement keys = new XmlElement("trim");
        keys.addAttribute(new Attribute("prefix", "("));
        keys.addAttribute(new Attribute("suffix", ")"));
        keys.addAttribute(new Attribute("suffixOverrides", ","));

        // Insert SQL Values组装
        XmlElement values = new XmlElement("trim");
        values.addAttribute(new Attribute("prefix", "values ("));
        values.addAttribute(new Attribute("suffix", ")"));
        values.addAttribute(new Attribute("suffixOverrides", ","));

        for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
            MethodUtils.generateColumnEqXmlElement(column, "record.");

            // 字段
            XmlElement conditionElement = new XmlElement("if");
            conditionElement.addAttribute(new Attribute("test", "record." + column.getJavaProperty() + " != null"));
            conditionElement.addElement(new TextElement(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(column) + ","));
            keys.addElement(conditionElement);

            // VALUES
            XmlElement conditionElement2 = new XmlElement("if");
            conditionElement2.addAttribute(new Attribute("test", "record." + column.getJavaProperty() + " != null"));
            conditionElement2.addElement(new TextElement(MyBatis3FormattingUtilities.getParameterClause(column, "record.") + ","));
            values.addElement(conditionElement2);

        }

        // FIX Generate Key
        GeneratedKey gk = introspectedTable.getGeneratedKey();
        if (gk != null) {
            IntrospectedColumn introspectedColumn = introspectedTable
                    .getColumn(gk.getColumn());
            // if the column is null, then it's a configuration error. The
            // warning has already been reported
            if (introspectedColumn != null) {
                if (gk.isJdbcStandard()) {
                    insertElement.addAttribute(new Attribute("useGeneratedKeys", "true")); //$NON-NLS-1$ //$NON-NLS-2$
                    insertElement.addAttribute(new Attribute("keyProperty", introspectedColumn.getJavaProperty())); //$NON-NLS-1$
                } else {
                    insertElement.addElement(getSelectKey(introspectedColumn, gk));
                }
            }
        }

        String sql = StringUtils.easyAppend("insert ignore into {} ", tableName);
        insertElement.addElement(new TextElement(sql));
        insertElement.addElement(keys);
        insertElement.addElement(values);

        parentElement.addElement(insertElement);
        return true;
    }

    /**
     * Copy From org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.InsertSelectiveElementGenerator
     *
     * @param introspectedColumn
     * @param generatedKey
     * @return
     */
    protected XmlElement getSelectKey(IntrospectedColumn introspectedColumn, GeneratedKey generatedKey) {
        String identityColumnType = introspectedColumn
                .getFullyQualifiedJavaType().getFullyQualifiedName();

        XmlElement answer = new XmlElement("selectKey"); //$NON-NLS-1$
        answer.addAttribute(new Attribute("resultType", identityColumnType)); //$NON-NLS-1$
        answer.addAttribute(new Attribute("keyProperty", introspectedColumn.getJavaProperty())); //$NON-NLS-1$
        answer.addAttribute(new Attribute("order", //$NON-NLS-1$
                generatedKey.getMyBatis3Order()));

        answer.addElement(new TextElement(generatedKey
                .getRuntimeSqlStatement()));

        return answer;
    }
}
