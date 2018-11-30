package com.ipukr.elephant.mybatis.plugins;

import com.ipukr.elephant.mybatis.plugins.ext.ServiceImplJavaFileGenerator;
import com.ipukr.elephant.mybatis.plugins.ext.ServiceJavaFileGenerator;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.CompilationUnit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wmw on 1/12/17.
 */
public class GeneratedServiceJavaFilePlugin extends PluginAdapter {

    public static final String ENABLE_SERVICE_LABEL = "Services";

    @Override
    public boolean validate(List<String> list) {
        return true;
    }


    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> generatedFile = new ArrayList<GeneratedJavaFile>();

        String label = introspectedTable.getTableConfigurationProperty(ENABLE_SERVICE_LABEL);
        // 是否自动生成 Service
        if (label!=null && label.equalsIgnoreCase("TRUE")) {
            String iTargetProject = properties.getProperty("TargetProject");
            if (iTargetProject == null || iTargetProject.equals("")) {
                iTargetProject = context.getJavaModelGeneratorConfiguration().getTargetProject();
            }

            {
                ServiceJavaFileGenerator mServiceJavaGenerator = new ServiceJavaFileGenerator(context, introspectedTable, properties);
                List<CompilationUnit> mCompilationUnits = mServiceJavaGenerator.getCompilationUnits();

                GeneratedJavaFile gif;
                for (Iterator iterator = mCompilationUnits.iterator(); iterator.hasNext(); generatedFile.add(gif)) {
                    CompilationUnit unit = (CompilationUnit) iterator.next();
                    gif = new GeneratedJavaFile(
                            unit,
                            iTargetProject,
                            context.getProperty("javaFileEncoding"),
                            context.getJavaFormatter());
                }
            }
            //ServiceImpl
            {
                ServiceImplJavaFileGenerator mServiceImplJavaGenerator = new ServiceImplJavaFileGenerator(context, introspectedTable, properties);
                List<CompilationUnit> mCompilationUnits2 = mServiceImplJavaGenerator.getCompilationUnits();

                GeneratedJavaFile gif;
                for (Iterator iterator = mCompilationUnits2.iterator(); iterator.hasNext(); generatedFile.add(gif)) {
                    CompilationUnit unit = (CompilationUnit) iterator.next();
                    gif = new GeneratedJavaFile(
                            unit,
                            iTargetProject,
                            context.getProperty("javaFileEncoding"),
                            context.getJavaFormatter());
                }
            }

        }

        return generatedFile;
    }


}
