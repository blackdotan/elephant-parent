package com.ipukr.elephant.mybatis.plugins;

import com.ipukr.elephant.mybatis.plugins.ext.EnumerationHandlerJavaFileGenerator;
import com.ipukr.elephant.mybatis.plugins.utils.ColumnUtils;
import com.ipukr.elephant.utils.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wmw on 1/12/17.
 */
public class GeneratedEnumerationHandlerJavaFilePlugin extends PluginAdapter {

    public static final String ENUMERATION_FLAT = "Enumerations";

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        this.recolumn(introspectedTable);
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        String cols = introspectedTable.getTableConfigurationProperty(ENUMERATION_FLAT);
        if ( cols !=null ) {
            String iTargetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
            String iModel = introspectedTable.getFullyQualifiedTable().getDomainObjectName();

            for (String col : cols.split(",")) {
                IntrospectedColumn column = introspectedTable.getColumn(col);
                if (column != null) {
                    String enumeration = StringUtils.easyAppend("{}.domain.{}{}", iTargetPackage, iModel, ColumnUtils.retClazzNameFormat(column));
                    String handler = StringUtils.easyAppend("{}.handler.{}{}Handler", iTargetPackage, iModel, ColumnUtils.retClazzNameFormat(column));
                    if ( field.getName().equals(column.getJavaProperty())) {
                        field.setType(new FullyQualifiedJavaType(enumeration));
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        String cols = introspectedTable.getTableConfigurationProperty(ENUMERATION_FLAT);
        if ( cols !=null ) {
            String iTargetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
            String iModel = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
            for (String col : cols.split(",")) {
                IntrospectedColumn column = introspectedTable.getColumn(col);
                if (column != null) {
                    String enumeration = StringUtils.easyAppend("{}.domain.{}{}", iTargetPackage, iModel, ColumnUtils.retClazzNameFormat(column));
                    if (method.getName().equalsIgnoreCase(StringUtils.easyAppend("set{}", ColumnUtils.retClazzNameFormat(column)))) {
                        List<Parameter> parameters = method.getParameters();
                        parameters.remove(0);
                        Parameter parameter = new Parameter(new FullyQualifiedJavaType(enumeration), column.getJavaProperty());
                        method.addParameter(0, parameter);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        String cols = introspectedTable.getTableConfigurationProperty(ENUMERATION_FLAT);
        if ( cols !=null ) {
            String iTargetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
            String iModel = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
            for (String col : cols.split(",")) {
                IntrospectedColumn column = introspectedTable.getColumn(col);
                if (column != null) {
                    String enumeration = StringUtils.easyAppend("{}.domain.{}{}", iTargetPackage, iModel, ColumnUtils.retClazzNameFormat(column));
                    if (method.getName().equalsIgnoreCase(StringUtils.easyAppend("get{}", ColumnUtils.retClazzNameFormat(column)))) {
                        method.setReturnType(new FullyQualifiedJavaType(enumeration));
                    }
                }
            }
        }
        return super.modelGetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        this.recolumn(introspectedTable);
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }


    @Override
    public boolean sqlMapBaseColumnListElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.recolumn(introspectedTable);
        return super.sqlMapBaseColumnListElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapResultMapWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return super.sqlMapResultMapWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> generatedFile = new ArrayList<GeneratedJavaFile>();

        String cols = introspectedTable.getTableConfigurationProperty(ENUMERATION_FLAT);

        // 是否需要自动生成枚举
        if ( cols !=null ) {
            String iTargetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
            String iModel = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
            // 获取处理字段
            for (String col : cols.split(",")) {
                // 遍历获取待处理Column
                IntrospectedColumn column = introspectedTable.getColumn(col);
                if (column != null) {
                    String enumeration = StringUtils.easyAppend("{}.domain.{}{}", iTargetPackage, iModel, ColumnUtils.retClazzNameFormat(column));
                    String handler = StringUtils.easyAppend("{}.handler.{}{}Handler", iTargetPackage, iModel, ColumnUtils.retClazzNameFormat(column));

                    column.setFullyQualifiedJavaType(new FullyQualifiedJavaType(enumeration));
                    column.setTypeHandler(handler);

                    EnumerationHandlerJavaFileGenerator generator = new EnumerationHandlerJavaFileGenerator(context, introspectedTable, properties, column);
                    List<CompilationUnit> mCompilationUnits = generator.getCompilationUnits();

                    GeneratedJavaFile gif;
                    for (Iterator iterator = mCompilationUnits.iterator(); iterator.hasNext(); generatedFile.add(gif)) {
                        CompilationUnit unit = (CompilationUnit) iterator.next();
                        gif = new GeneratedJavaFile(
                                unit,
                                context.getJavaModelGeneratorConfiguration().getTargetProject(),
                                context.getProperty("javaFileEncoding"),
                                context.getJavaFormatter());
                    }
                }
            }
        }
        return generatedFile;
    }

    private void recolumn(IntrospectedTable introspectedTable) {
        String cols = introspectedTable.getTableConfigurationProperty(ENUMERATION_FLAT);
        if ( cols !=null ) {
            String iTargetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
            String iModel = introspectedTable.getFullyQualifiedTable().getDomainObjectName();

            for (String col : cols.split(",")) {
                IntrospectedColumn column = introspectedTable.getColumn(col);
                if (column != null) {
                    String enumeration = StringUtils.easyAppend("{}.domain.{}{}", iTargetPackage, iModel, ColumnUtils.retClazzNameFormat(column));
                    String handler = StringUtils.easyAppend("{}.handler.{}{}Handler", iTargetPackage, iModel, ColumnUtils.retClazzNameFormat(column));
                    column.setFullyQualifiedJavaType(new FullyQualifiedJavaType(enumeration));
                    column.setTypeHandler(handler);
                }
            }
        }
    }


}
