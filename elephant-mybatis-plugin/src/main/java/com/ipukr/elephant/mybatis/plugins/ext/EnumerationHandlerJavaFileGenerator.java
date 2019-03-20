package com.ipukr.elephant.mybatis.plugins.ext;

import com.ipukr.elephant.common.Identifiable;
import com.ipukr.elephant.mybatis.plugins.utils.ColumnUtils;
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
public class EnumerationHandlerJavaFileGenerator extends AbstractJavaGenerator {
    private Context context;
    private Properties properties;
    private IntrospectedTable introspectedTable;
    private IntrospectedColumn column;


    public EnumerationHandlerJavaFileGenerator(Context context, IntrospectedTable introspectedTable, Properties properties, IntrospectedColumn column) {
        this.context = context;
        this.introspectedTable = introspectedTable;
        this.properties = properties;
        this.column = column;
        setContext(this.context);
        setIntrospectedTable(this.introspectedTable);
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();

        boolean isOverrideEnumeration = properties.getProperty("Override")!=null &&
                properties.getProperty("Override").equalsIgnoreCase("TRUE");

        if (isOverrideEnumeration) {
            answer.add(this.generateEnumerationJavaFile(this.column));
        }

        answer.add(this.generateEnumerationHandlerJavaFile(this.column));

        return answer;
    }

    private CompilationUnit generateEnumerationJavaFile(IntrospectedColumn column) {
        String iTargetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        String iModel = introspectedTable.getFullyQualifiedTable().getDomainObjectName();

        String model = StringUtils.easyAppend("{}{}", iModel, ColumnUtils.retClazzNameFormat(column));
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

        return enumeration;
    }

    private CompilationUnit generateEnumerationHandlerJavaFile(IntrospectedColumn column) {
        // 拼接类名
        String iTargetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        String iModel = introspectedTable.getFullyQualifiedTable().getDomainObjectName();


        String model = StringUtils.easyAppend("{}{}Handler", iModel, ColumnUtils.retClazzNameFormat(column));
        String clazz = StringUtils.easyAppend("{}.handler.{}", iTargetPackage, model);

        // 初始化创建
        TopLevelClass handler = new TopLevelClass(new FullyQualifiedJavaType(clazz));
        handler.setVisibility(JavaVisibility.PUBLIC);

        // import
        String model_ = StringUtils.easyAppend("{}{}", iModel, ColumnUtils.retClazzNameFormat(column));
        String clazz_ = StringUtils.easyAppend("{}.domain.{}", iTargetPackage, model_);
        FullyQualifiedJavaType importClazz = new FullyQualifiedJavaType(clazz_);

        // extends
        if (JdbcType.forCode(column.getJdbcType()) == JdbcType.INTEGER) {
            FullyQualifiedJavaType supperClass = new FullyQualifiedJavaType("com.ipukr.elephant.common.mybatis.handler.IntegerToEnumHandler");
            supperClass.addTypeArgument(new FullyQualifiedJavaType(model_));
            handler.setSuperClass(supperClass);
        } else if (JdbcType.forCode(column.getJdbcType()) == JdbcType.VARCHAR){
            FullyQualifiedJavaType supperClass = new FullyQualifiedJavaType("com.ipukr.elephant.common.mybatis.handler.CharacterToEnumHandler");
            supperClass.addTypeArgument(new FullyQualifiedJavaType(model_));
            handler.setSuperClass(supperClass);
        } else {
            throw new RuntimeException("自动生成枚举属性失败，当前插件只支持INTEGER/VARCHAR");
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
        imports.add(new FullyQualifiedJavaType("com.ipukr.elephant.common.mybatis.handler.CharacterToEnumHandler"));
        imports.add(new FullyQualifiedJavaType(clazz));
        imports.add(new FullyQualifiedJavaType(clazz_));
        handler.addImportedTypes(imports);

        return handler;
    }

    public CompilationUnit generateDomainJavaFile(IntrospectedColumn column, FullyQualifiedJavaType iDomainClass, Set<FullyQualifiedJavaType> imports) {
        String iTargetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        String iModel = introspectedTable.getFullyQualifiedTable().getDomainObjectName();

        String model = StringUtils.easyAppend("{}{}", iModel, ColumnUtils.retClazzNameFormat(column));
        String clazz = StringUtils.easyAppend("{}.domain.{}", iTargetPackage, model);

        // 实例化 TopLevelEnumeration
        FullyQualifiedJavaType iFullyQualifiedJavaType = new FullyQualifiedJavaType(clazz);
        TopLevelClass domain = new TopLevelClass(iFullyQualifiedJavaType);
        domain.setVisibility(JavaVisibility.PUBLIC);

        domain.addImportedTypes(imports);

        return domain;
    }

    public CompilationUnit generateHandlerJavaFile(IntrospectedColumn column, FullyQualifiedJavaType iModelClass, FullyQualifiedJavaType iSupperClass, Set<FullyQualifiedJavaType> imports) {
        // 拼接类名
        String iTargetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        String iModel = introspectedTable.getFullyQualifiedTable().getDomainObjectName();

        String model = StringUtils.easyAppend("{}{}Handler", iModel, ColumnUtils.retClazzNameFormat(column));
        String clazz = StringUtils.easyAppend("{}.handler.{}", iTargetPackage, model);

        // 初始化创建
        TopLevelClass handler = new TopLevelClass(clazz);
        handler.setVisibility(JavaVisibility.PUBLIC);
        handler.setSuperClass(iSupperClass);

        // method
        Method method = new Method("getGeneric");
        FullyQualifiedJavaType retClazz = new FullyQualifiedJavaType("java.lang.Class");
        retClazz.addTypeArgument(iModelClass);
        method.setReturnType(retClazz);
        method.addAnnotation("@Override");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addBodyLine(StringUtils.easyAppend("return {}.class;", iModelClass.getShortName()));
        handler.addMethod(method);

        handler.addImportedTypes(imports);
        return handler;
    }
}
