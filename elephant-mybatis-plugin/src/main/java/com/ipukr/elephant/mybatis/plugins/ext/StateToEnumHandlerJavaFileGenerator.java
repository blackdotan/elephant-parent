package com.ipukr.elephant.mybatis.plugins.ext;

import com.ipukr.elephant.utils.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.config.Context;

import java.util.*;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/7/30.
 */
@Deprecated
public class StateToEnumHandlerJavaFileGenerator extends AbstractJavaGenerator {
    private Context context;
    private Properties properties;
    private IntrospectedTable introspectedTable;


    public StateToEnumHandlerJavaFileGenerator(Context context, IntrospectedTable introspectedTable, Properties properties) {
        this.context = context;
        this.introspectedTable = introspectedTable;
        this.properties = properties;
        setContext(this.context);
        setIntrospectedTable(this.introspectedTable);
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();

        IntrospectedColumn column = null;
        for(IntrospectedColumn iIntrospectedColumn : introspectedTable.getBaseColumns()) {
            if (iIntrospectedColumn.getActualColumnName().equalsIgnoreCase("State")) {
                column = iIntrospectedColumn;
            }
        }
        if (column!=null) {
            // 拼接类名
            String iTargetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
            String iModel = introspectedTable.getFullyQualifiedTable().getDomainObjectName();


            String model = StringUtils.easyAppend("{}StateHandler", iModel);
            String clazz = StringUtils.easyAppend("{}.handler.{}", iTargetPackage, model);

            // 初始化创建
            TopLevelClass handler = new TopLevelClass(new FullyQualifiedJavaType(clazz));
            handler.setVisibility(JavaVisibility.PUBLIC);

            // import
            String model_ = StringUtils.easyAppend("{}State", iModel);
            String clazz_ = StringUtils.easyAppend("{}.domain.{}", iTargetPackage, model_);
            FullyQualifiedJavaType importClazz = new FullyQualifiedJavaType(clazz_);

            // extends
            if(JdbcType.forCode(column.getJdbcType()) == JdbcType.INTEGER) {
                FullyQualifiedJavaType supperClass = new FullyQualifiedJavaType("com.ipukr.elephant.common.mybatis.handler.IntegerToEnumHandler");
                supperClass.addTypeArgument(new FullyQualifiedJavaType(model_));
                handler.setSuperClass(supperClass);
            }

            // method
            Method method = new Method("getGeneric");
            FullyQualifiedJavaType retClazz = new FullyQualifiedJavaType("java.lang.Class");
            retClazz.addTypeArgument(new FullyQualifiedJavaType(model_));
            method.setReturnType(retClazz);
            method.addAnnotation("@Override");
            method.setVisibility(JavaVisibility.PUBLIC);
            method.addBodyLine(StringUtils.easyAppend("return {}.class;", model_));
            handler.addMethod(method);

            // import
            Set<FullyQualifiedJavaType> imports = new HashSet<FullyQualifiedJavaType>();
            imports.add(new FullyQualifiedJavaType("com.ipukr.elephant.common.mybatis.handler.IntegerToEnumHandler"));
            imports.add(new FullyQualifiedJavaType(clazz));
            imports.add(new FullyQualifiedJavaType(clazz_));
            handler.addImportedTypes(imports);

            answer.add(handler);
        }
        return answer;
    }
}
