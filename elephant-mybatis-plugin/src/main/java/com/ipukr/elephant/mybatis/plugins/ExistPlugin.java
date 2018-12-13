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

import java.util.Iterator;
import java.util.List;

/**
 * 记录存在判断
 *
 * Created by wmw on 4/27/16.
 */
public class ExistPlugin extends PluginAdapter {


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

        iMethod.setName("exist");
        iMethod.setVisibility(method.getVisibility());
        iMethod.setReturnType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        iMethod.getParameters().clear();
        iMethod.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), "record", "@Param(\"record\")"));
        iMethod.addAnnotation("/**");
        iMethod.addAnnotation(" * 判断匹配记录是否存在");
        iMethod.addAnnotation(" **/");
        interfaze.addMethod(iMethod);

        Method iMethod2 = new Method();

        iMethod2.setName("existPrimaryKey");
        iMethod2.setVisibility(method.getVisibility());
        iMethod2.setReturnType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
        iMethod2.getParameters().clear();
        Iterator<IntrospectedColumn> it = introspectedTable.getPrimaryKeyColumns().iterator();
        int count = 1;
        while (it.hasNext()){
            IntrospectedColumn iIntrospectedColumn = it.next();
            iMethod2.addParameter(
                    new Parameter(new FullyQualifiedJavaType(iIntrospectedColumn.getFullyQualifiedJavaType().getFullyQualifiedName()), "pk", "@Param(\"pk\")"));
        }
        iMethod2.addAnnotation("/**");
        iMethod2.addAnnotation(" * 判断匹配主键是否存在");
        iMethod2.addAnnotation(" **/");
        interfaze.addMethod(iMethod2);

        return true;
    }



    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名
        String baseColumn = introspectedTable.getBaseColumnListId();

        XmlElement parentElement = document.getRootElement();

        // 生成 XML 配置
        XmlElement existElement = new XmlElement("select");
        existElement.addAttribute(new Attribute("id", "exist"));
        existElement.addAttribute(new Attribute("resultType", "boolean"));
        existElement.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        existElement.addElement(new TextElement("select count(1) from #tableName where 1=1 ".replace("#tableName", tableName)));

        // 生成判断语句

        for (IntrospectedColumn column : introspectedTable.getAllColumns()) {
            existElement.addElement(MethodUtils.generateColumnEqXmlElement(column, "record."));
        }
        parentElement.addElement(existElement);



        XmlElement existPrimaryKeyElement = new XmlElement("select");
        existPrimaryKeyElement.addAttribute(new Attribute("id", "existPrimaryKey"));
        existPrimaryKeyElement.addAttribute(new Attribute("resultType", "boolean"));
        StringBuffer buffer = new StringBuffer();

        for (IntrospectedColumn iIntrospectedColumn: introspectedTable.getPrimaryKeyColumns()){
            buffer.append(MethodUtils.generateCondition(iIntrospectedColumn));
        }

        String  sql = StringUtils.easyAppend("select count(1) from {} where 1=1 {}", tableName, buffer);
        existPrimaryKeyElement.addElement(new TextElement(sql));
        parentElement.addElement(existPrimaryKeyElement);


        return true;
    }
}
