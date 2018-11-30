package com.ipukr.elephant.mybatis.plugins.ext;

import com.ipukr.elephant.common.Identifiable;
import com.ipukr.elephant.utils.StringUtils;
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
public class StateToEnumJavaFileGenerator extends AbstractJavaGenerator {
    private Context context;
    private Properties properties;
    private IntrospectedTable introspectedTable;


    public StateToEnumJavaFileGenerator(Context context, IntrospectedTable introspectedTable, Properties properties) {
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

            String model = StringUtils.easyAppend("{}State", iModel);
            String clazz = StringUtils.easyAppend("{}.domain.{}", iTargetPackage, model);

            // 实例化 TopLevelEnumeration
            FullyQualifiedJavaType iFullyQualifiedJavaType = new FullyQualifiedJavaType(clazz);
            TopLevelEnumeration enumeration = new TopLevelEnumeration(iFullyQualifiedJavaType);
            enumeration.setVisibility(JavaVisibility.PUBLIC);
            enumeration.addEnumConstant("状态(1);");


            // 属性
            Field field = new Field("value", new FullyQualifiedJavaType(Integer.class.getName()));
            field.setVisibility(JavaVisibility.PRIVATE);
            enumeration.addField(field);

            // 构造方法
            Method constructor = new Method(StringUtils.easyAppend("{}", model));
            constructor.setConstructor(true);
            Parameter parameter = new Parameter(new FullyQualifiedJavaType(Integer.class.getName()), "value");
            constructor.addParameter(parameter);
            constructor.addBodyLine("this.value = value;");
            enumeration.addMethod(constructor);

            // 继承
            FullyQualifiedJavaType superInt = new FullyQualifiedJavaType(Identifiable.class.getSimpleName());
            superInt.addTypeArgument(new FullyQualifiedJavaType(Integer.class.getName()));
            enumeration.addSuperInterface(superInt);

            // 继承方法
            Method method = new Method("getId");
            method.setReturnType(new FullyQualifiedJavaType(Integer.class.getName()));
            method.setVisibility(JavaVisibility.PUBLIC);
            method.addAnnotation("@Override");
            method.setVisibility(JavaVisibility.PUBLIC);
            method.addBodyLine("return this.value;");
            enumeration.addMethod(method);

            // Import
            Set<FullyQualifiedJavaType> imports = new HashSet<FullyQualifiedJavaType>();
            imports.add(new FullyQualifiedJavaType("com.ipukr.elephant.common.Identifiable"));
            enumeration.addImportedTypes(imports);

            answer.add(enumeration);
        }
        return answer;
    }
}
