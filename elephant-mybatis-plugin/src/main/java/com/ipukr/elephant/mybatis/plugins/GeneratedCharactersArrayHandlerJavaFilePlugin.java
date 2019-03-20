package com.ipukr.elephant.mybatis.plugins;

import com.ipukr.elephant.common.mybatis.handler.JsonArrayHandler;
import com.ipukr.elephant.mybatis.plugins.ext.EnumerationHandlerJavaFileGenerator;
import com.ipukr.elephant.mybatis.plugins.utils.ColumnUtils;
import com.ipukr.elephant.utils.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.sql.JDBCType;
import java.util.*;

/**
 * 生成 List<String> 属性
 * Created by wmw on 1/12/17.
 */
public class GeneratedCharactersArrayHandlerJavaFilePlugin extends PluginAdapter {

    public static final String ENUMERATION_FLAT = "CharactersArray";

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        this.recolumn(introspectedTable);
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    /**
     * 生成文件
     * @param field
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        String cols = introspectedTable.getTableConfigurationProperty(ENUMERATION_FLAT);
        if ( cols !=null ) {
            String iTargetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
            String iModel = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
            // 获取待处理每个字段
            for (String col : cols.split(",")) {
                IntrospectedColumn column = introspectedTable.getColumn(col);
                if (column != null) {
                    // 判断当前属性是否为待修改属性，修改属性类型为枚举类型
                    if ( field.getName().equals(column.getJavaProperty())) {
                        // 字段<参数>类型
                        FullyQualifiedJavaType iModelClass = new FullyQualifiedJavaType(String.class.getName());
                        // 字段类型
                        FullyQualifiedJavaType iCharactersArrayColumnType = new FullyQualifiedJavaType(List.class.getName());
                        iCharactersArrayColumnType.addTypeArgument(iModelClass);
                        // 修改Field类型
                        field.setType(iCharactersArrayColumnType);

                        // 新增Import
                        Set<FullyQualifiedJavaType> imports = new HashSet<FullyQualifiedJavaType>();
                        imports.add(new FullyQualifiedJavaType("java.lang.String"));
                        imports.add(iCharactersArrayColumnType);
                        topLevelClass.addImportedTypes(imports);
                    }
                }
            }
        }
        return true;
    }

    /**
     * 修改Setter方法
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        String cols = introspectedTable.getTableConfigurationProperty(ENUMERATION_FLAT);
        if ( cols !=null ) {
            String iTargetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
            String iModel = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
            for (String col : cols.split(",")) {
                IntrospectedColumn column = introspectedTable.getColumn(col);
                if (column != null) {
                    // 字段<参数>类型
                    FullyQualifiedJavaType iModelClass = new FullyQualifiedJavaType(String.class.getName());
                    // 字段类型
                    FullyQualifiedJavaType iCharactersArrayColumnType = new FullyQualifiedJavaType(List.class.getName());
                    iCharactersArrayColumnType.addTypeArgument(iModelClass);
                    // 修改Setter方法
                    if (method.getName().equalsIgnoreCase(StringUtils.easyAppend("set{}", ColumnUtils.retClazzNameFormat(column)))) {
                        List<Parameter> parameters = method.getParameters();
                        parameters.remove(0);
                        Parameter parameter = new Parameter(iCharactersArrayColumnType, column.getJavaProperty());
                        method.addParameter(0, parameter);
                    }
                }
            }
        }
        return true;
    }

    /**
     * 修改Getter方法
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        String cols = introspectedTable.getTableConfigurationProperty(ENUMERATION_FLAT);
        if ( cols !=null ) {
            String iTargetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
            String iModel = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
            for (String col : cols.split(",")) {
                IntrospectedColumn column = introspectedTable.getColumn(col);
                if (column != null) {
                    // 字段<参数>类型
                    FullyQualifiedJavaType iModelClass = new FullyQualifiedJavaType(String.class.getName());
                    // 字段类型
                    FullyQualifiedJavaType iCharactersArrayColumnType = new FullyQualifiedJavaType(List.class.getName());
                    iCharactersArrayColumnType.addTypeArgument(iModelClass);
                    // 修改Getter方法
                    if (method.getName().equalsIgnoreCase(StringUtils.easyAppend("get{}", ColumnUtils.retClazzNameFormat(column)))) {
                        method.setReturnType(iCharactersArrayColumnType);
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
        this.recolumn(introspectedTable);
        return super.sqlMapResultMapWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    /**
     * 生成Java文件
     * @param introspectedTable
     * @return
     */
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
                    // 字段<参数>类型
                    FullyQualifiedJavaType iModelClass = new FullyQualifiedJavaType(String.class.getName());
                    // 字段类型
                    FullyQualifiedJavaType iCharactersArrayColumnType = new FullyQualifiedJavaType(List.class.getName());
                    iCharactersArrayColumnType.addTypeArgument(iModelClass);

                    // 修改Column类型为字段类型
                    column.setFullyQualifiedJavaType(iCharactersArrayColumnType);

                    // 修改Column类型Handler类
                    String handler = StringUtils.easyAppend("{}.handler.{}{}Handler", iTargetPackage, iModel, ColumnUtils.retClazzNameFormat(column));
                    column.setTypeHandler(handler);

                    // 自动生成
                    EnumerationHandlerJavaFileGenerator generator = new EnumerationHandlerJavaFileGenerator(context, introspectedTable, properties, column);

                    FullyQualifiedJavaType supperClass = new FullyQualifiedJavaType(JsonArrayHandler.class.getSimpleName());
                    supperClass.addTypeArgument(iModelClass);
                    Set<FullyQualifiedJavaType> set = new HashSet<FullyQualifiedJavaType>();
                    set.add(iModelClass);
                    set.add(supperClass);
                    set.add(new FullyQualifiedJavaType(JsonArrayHandler.class.getCanonicalName()));


                    boolean isOverrideEnumeration = properties.getProperty("Override")!=null && properties.getProperty("Override").equalsIgnoreCase("TRUE");

                    if (isOverrideEnumeration) {
                        List<CompilationUnit> answer = Arrays.asList(generator.generateHandlerJavaFile(column, iModelClass, supperClass, set));
                        GeneratedJavaFile gif;
                        for (Iterator iterator = answer.iterator(); iterator.hasNext(); generatedFile.add(gif)) {
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
                    String handler = StringUtils.easyAppend("{}.handler.{}{}Handler", iTargetPackage, iModel, ColumnUtils.retClazzNameFormat(column));

                    // 字段<参数>类型
                    FullyQualifiedJavaType iModelClass = new FullyQualifiedJavaType(String.class.getCanonicalName());
                    // 字段类型
                    FullyQualifiedJavaType iCharactersArrayColumnType = new FullyQualifiedJavaType(List.class.getName());
                    iCharactersArrayColumnType.addTypeArgument(iModelClass);


                    // 字段解析器类型
                    FullyQualifiedJavaType iHandlerClass = new FullyQualifiedJavaType(handler);

                    column.setFullyQualifiedJavaType(iCharactersArrayColumnType);
                    column.setTypeHandler(iHandlerClass.getFullyQualifiedName());
                    column.setJdbcType(JDBCType.VARCHAR.getVendorTypeNumber());
                }
            }
        }
    }


}
