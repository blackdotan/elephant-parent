package com.ipukr.elephant.mybatis.plugins;

import com.ipukr.elephant.mybatis.plugins.ext.ControllerJavaFileGenerator;
import com.ipukr.elephant.mybatis.plugins.ext.ControllerJavaFileGeneratorV2;
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
@Deprecated
public class GeneratedControllerJavaFilePlugin extends PluginAdapter {

    public static final String CONTROLLER_FLAG= "Controllers";
    public static final String JAVAFILE_GENERATOR= "JavaFileGenerator";

    public GeneratedControllerJavaFilePlugin() {

    }

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> generatedFile = new ArrayList<GeneratedJavaFile>();

        String label = introspectedTable.getTableConfigurationProperty(CONTROLLER_FLAG);

        if (label!=null) {
            String iTargetProject = properties.getProperty("TargetProject");
            if (iTargetProject == null || iTargetProject.equals("")) {
                iTargetProject = context.getJavaModelGeneratorConfiguration().getTargetProject();
            }
            String iJavaFileGenerator = properties.getProperty(JAVAFILE_GENERATOR);

            if (iJavaFileGenerator.equalsIgnoreCase("ControllerJavaFileGeneratorV2")) {
                ControllerJavaFileGeneratorV2 contoller = new ControllerJavaFileGeneratorV2(context, introspectedTable, properties);
                List<CompilationUnit> contollerUnits = contoller.getCompilationUnits();

                GeneratedJavaFile gif;
                for (Iterator iterator = contollerUnits.iterator(); iterator.hasNext(); generatedFile.add(gif)) {
                    CompilationUnit unit = (CompilationUnit) iterator.next();
                    gif = new GeneratedJavaFile(
                            unit,
                            iTargetProject,
                            context.getProperty("javaFileEncoding"),
                            context.getJavaFormatter());
                }
            } else {
                ControllerJavaFileGenerator contoller = new ControllerJavaFileGenerator(context, introspectedTable, properties);
                List<CompilationUnit> contollerUnits = contoller.getCompilationUnits();

                GeneratedJavaFile gif;
                for (Iterator iterator = contollerUnits.iterator(); iterator.hasNext(); generatedFile.add(gif)) {
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
