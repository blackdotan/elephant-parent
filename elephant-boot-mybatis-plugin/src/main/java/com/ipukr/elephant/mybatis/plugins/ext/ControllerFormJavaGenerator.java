package com.ipukr.elephant.mybatis.plugins.ext;

import com.ipukr.elephant.utils.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.config.Context;

import java.util.*;

/**
 * Created by wmw on 1/12/17.
 */
@Deprecated
public class ControllerFormJavaGenerator extends AbstractJavaGenerator {
    public static final String PLACE_HOLDER_BASECONTROLLERPACKAGE = "BaseControllerPackage";
    public static final String PLACE_HOLDER_BASECONTROLLERFORMPACKAGE = "BaseControllerFormPackage";
    public static final String PLACE_HOLDER_BASESERVICEPACKAGE = "BaseServicePackage";
    public static final String PLACE_HOLDER_BASESERVICEIMPLPACKAGE = "BaseServiceImplPackage";

    private Context context;
    private Properties properties;
    private IntrospectedTable introspectedTable;

    public ControllerFormJavaGenerator(Context context, IntrospectedTable introspectedTable, Properties properties) {
        this.context = context;
        this.introspectedTable = introspectedTable;
        this.properties = properties;
        setContext(this.context);
        setIntrospectedTable(this.introspectedTable);
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        /**
         * 获取常量
         * */
        String BaseControllerFormPackage = properties.getProperty(PLACE_HOLDER_BASECONTROLLERFORMPACKAGE);

        String targetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        String Model = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        String clazz = StringUtils.easyAppend("{}.{}Form", BaseControllerFormPackage, Model);

        String PrimaryType = "String";
        Iterator<IntrospectedColumn> it = introspectedTable.getPrimaryKeyColumns().iterator();
        if(it.hasNext()){
            PrimaryType = it.next().getFullyQualifiedJavaType().getShortName();
        }


        /**
         * 生成Form类
         * */
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(clazz);
        TopLevelClass tlc = new TopLevelClass(type);
        tlc.setVisibility(JavaVisibility.PUBLIC);
        tlc.addImportedType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));

        /**
         * 生成类属性
         * */
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
        for(IntrospectedColumn column : columns){
            Field field = new Field();
            field.setName(column.getJavaProperty());
            field.setType(column.getFullyQualifiedJavaType());
            field.setVisibility(JavaVisibility.PRIVATE);
            tlc.addField(field);

            String setterName = StringUtils.easyAppend("set{}{}",
                    column.getJavaProperty().substring(0,1).toUpperCase(),
                    column.getJavaProperty().substring(1));
            String getterName = StringUtils.easyAppend("get{}{}",
                    column.getJavaProperty().substring(0,1).toUpperCase(),
                    column.getJavaProperty().substring(1));

            Method getter = new Method(getterName);
            getter.setVisibility(JavaVisibility.PUBLIC);
            getter.setReturnType(column.getFullyQualifiedJavaType());
            getter.addBodyLine("return this.{};".replace("{}", column.getJavaProperty()));
            tlc.addMethod(getter);


            Method setter = new Method(setterName);
            setter.setVisibility(JavaVisibility.PUBLIC);
            setter.addBodyLine("this.{} = {};".replace("{}", column.getJavaProperty()));
            Parameter p1 = new Parameter(column.getFullyQualifiedJavaType(), column.getJavaProperty());
            setter.addParameter(p1);
            tlc.addMethod(setter);

        }


        tlc.addMethod(validate(Model));

        /**
         *
         * 依赖导入
         * */
        Set<FullyQualifiedJavaType> imports = new HashSet<FullyQualifiedJavaType>();
        imports.add(new FullyQualifiedJavaType("java.util.Date"));
        tlc.addImportedTypes(imports);


        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        answer.add(tlc);
        return answer;
    }

    private Method validate(String Model){
        Method method = new Method("validate");
        method.setReturnType(new FullyQualifiedJavaType("boolean"));
        method.setVisibility(JavaVisibility.PUBLIC);

        StringBuffer condition = new StringBuffer(" 1 == 1\n");
        for(IntrospectedColumn column : introspectedTable.getBaseColumns()){
            if(!column.isNullable()){
                condition.append("\t\t\t\t&& this.{} != null \n".replace("{}", column.getJavaProperty()));
                if(column.getFullyQualifiedJavaType().getShortName().equals("String")){
                    condition.append("\t\t\t\t&&!this.{}.equals(\"\") \n".replace("{}", column.getJavaProperty()));
                    condition.append("\t\t\t\t&& this.{}.length()<={LEN} \n"
                            .replace("{}", column.getJavaProperty())
                            .replace("{LEN}", String.valueOf(column.getLength()) ));
                }
            }
        }

        Integer LAST_LINE_DELIMITER = condition.length()-1;
        condition.deleteCharAt(LAST_LINE_DELIMITER);

        StringBuffer body = new StringBuffer();
        body.append("if({}){\n")
                .append("\t\t\treturn true;\n")
                .append("\t\t} else{\n")
                .append("\t\t\treturn false;\n")
                .append("\t\t}");

        method.addBodyLine(body.toString().replace("{}", condition.toString()));
        return method;
    }


}
