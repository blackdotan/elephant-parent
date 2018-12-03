package com.ipukr.elephant.mybatis.plugins;

import com.ipukr.elephant.mybatis.plugins.ext.StateToEnumHandlerJavaFileGenerator;
import com.ipukr.elephant.mybatis.plugins.ext.StateToEnumJavaFileGenerator;
import com.ipukr.elephant.utils.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.ColumnOverride;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wmw on 1/12/17.
 */
@Deprecated
public class GeneratedStateHandlerJavaFilePlugin extends PluginAdapter {

    public static final String ENUMERATION_FLAT = "Enumerations";

    @Override
    public boolean validate(List<String> list) {
        return true;
    }




    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String cols = introspectedTable.getTableConfigurationProperty(ENUMERATION_FLAT);

        if ( cols !=null ) {
            String iTargetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
            String iModel = introspectedTable.getFullyQualifiedTable().getDomainObjectName();

            for (String col : cols.split(",")) {
                IntrospectedColumn column = introspectedTable.getColumn(col);
                if (column != null) {
                    String enumeration = StringUtils.easyAppend("{}.domain.{}{}", iTargetPackage, iModel, column.getActualColumnName());
                    String handler = StringUtils.easyAppend("{}.handler.{}{}Handler", iTargetPackage, iModel, column.getActualColumnName());

                    column.setFullyQualifiedJavaType(new FullyQualifiedJavaType(enumeration));
                    column.setTypeHandler(handler);

                    ColumnOverride iColumnOverride = new ColumnOverride(column.getActualColumnName());
                    iColumnOverride.setJavaType(enumeration);
                    iColumnOverride.setTypeHandler(handler);
                    iColumnOverride.setJdbcType("INTEGER");
                    introspectedTable.getTableConfiguration().addColumnOverride(iColumnOverride);
                }
            }
        }
        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> generatedFile = new ArrayList<GeneratedJavaFile>();

        String label = introspectedTable.getTableConfigurationProperty(ENUMERATION_FLAT);

        // 是否自动生成 Service
        if (label!=null && label.equalsIgnoreCase("TRUE")) {
            IntrospectedColumn column = null;
            for(IntrospectedColumn iIntrospectedColumn : introspectedTable.getBaseColumns()) {
                if (iIntrospectedColumn.getActualColumnName().equals("State")) {
                    column = iIntrospectedColumn;
                }
            }
            if (column!=null) {
                String iTargetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
                String iModel = introspectedTable.getFullyQualifiedTable().getDomainObjectName();

                String enumeration = StringUtils.easyAppend("{}.domain.{}State", iTargetPackage, iModel);
                String handler = StringUtils.easyAppend("{}.handler.{}StateHandler", iTargetPackage, iModel);

                column.setFullyQualifiedJavaType(new FullyQualifiedJavaType(enumeration));
                column.setTypeHandler(handler);

                {
                    StateToEnumJavaFileGenerator mServiceJavaGenerator = new StateToEnumJavaFileGenerator(context, introspectedTable, properties);
                    List<CompilationUnit> mCompilationUnits = mServiceJavaGenerator.getCompilationUnits();

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
                //ServiceImpl
                {
                    StateToEnumHandlerJavaFileGenerator mServiceImplJavaGenerator = new StateToEnumHandlerJavaFileGenerator(context, introspectedTable, properties);
                    List<CompilationUnit> mCompilationUnits2 = mServiceImplJavaGenerator.getCompilationUnits();

                    GeneratedJavaFile gif;
                    for (Iterator iterator = mCompilationUnits2.iterator(); iterator.hasNext(); generatedFile.add(gif)) {
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


}
