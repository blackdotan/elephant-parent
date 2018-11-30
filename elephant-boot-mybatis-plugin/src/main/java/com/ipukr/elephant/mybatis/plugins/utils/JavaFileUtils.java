package com.ipukr.elephant.mybatis.plugins.utils;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.ipukr.elephant.utils.DataUtils;
import com.ipukr.elephant.utils.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;

import java.io.IOException;
import java.util.*;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/8/5.
 */
public class JavaFileUtils {


    /**
     * 生成Form Java File
     * @param iIntrospectedTable
     * @param iFormClazzBasePackage
     * @return
     */
    public static CompilationUnit generateFormJavaFile(IntrospectedTable iIntrospectedTable, String iFormClazzBasePackage) {
        String iModel = iIntrospectedTable.getFullyQualifiedTable().getDomainObjectName();
        String iForm = StringUtils.easyAppend("{}Form", iModel);
        String iFormClazz = StringUtils.easyAppend("{}.{}", iFormClazzBasePackage, iForm);
        return generateFormJavaFile(iIntrospectedTable, new FullyQualifiedJavaType(iFormClazz));
    }
    /**
     * 生成Form Java File
     * @param iIntrospectedTable
     * @param iFormType
     * @return
     */
    public static CompilationUnit generateFormJavaFile(IntrospectedTable iIntrospectedTable, FullyQualifiedJavaType iFormType) {
        Set<FullyQualifiedJavaType> imports = new HashSet<FullyQualifiedJavaType>();
        /**
         * 生成Form类
         * */
        TopLevelClass tlc = new TopLevelClass(iFormType);
        tlc.setVisibility(JavaVisibility.PUBLIC);
        tlc.addImportedType(new FullyQualifiedJavaType(iIntrospectedTable.getBaseRecordType()));

        /**
         * 生成类属性
         * */
        List<IntrospectedColumn> columns = iIntrospectedTable.getAllColumns();
        for(IntrospectedColumn column : columns){
            Field field = new Field();
            field.setName(column.getJavaProperty());
            field.setType(column.getFullyQualifiedJavaType());
            field.setVisibility(JavaVisibility.PRIVATE);
            tlc.addField(field);
            tlc.addMethod(MethodUtils.generateGetMethod(field));
            tlc.addMethod(MethodUtils.generateSetMethod(field));
            imports.add(column.getFullyQualifiedJavaType());
        }

        {
            Method method = new Method("validate");
            method.setReturnType(new FullyQualifiedJavaType("boolean"));
            method.setVisibility(JavaVisibility.PUBLIC);

            StringBuffer condition = new StringBuffer(" 1 == 1\n");
            for (IntrospectedColumn column : iIntrospectedTable.getBaseColumns()) {
                List<IntrospectedColumn> pkcols = iIntrospectedTable.getPrimaryKeyColumns();

                if (!pkcols.contains(column) && !column.isNullable()) {
                    condition.append("\t\t\t\t&& this.{} != null \n".replace("{}", column.getJavaProperty()));
                    if (column.getFullyQualifiedJavaType().getShortName().equals("String")) {
                        condition.append("\t\t\t\t&&!this.{}.equals(\"\") \n".replace("{}", column.getJavaProperty()));
                        condition.append("\t\t\t\t&& this.{}.length()<={LEN} \n"
                                .replace("{}", column.getJavaProperty())
                                .replace("{LEN}", String.valueOf(column.getLength())));
                    }
                }
            }

            Integer LAST_LINE_DELIMITER = condition.length() - 1;
            condition.deleteCharAt(LAST_LINE_DELIMITER);

            StringBuffer body = new StringBuffer();
            body.append("if({}){\n")
                    .append("\t\t\treturn true;\n")
                    .append("\t\t} else{\n")
                    .append("\t\t\treturn false;\n")
                    .append("\t\t}");

            method.addBodyLine(body.toString().replace("{}", condition.toString()));
            tlc.addMethod(method);
        }

        tlc.addImportedTypes(imports);

        return tlc;
    }

    public static CompilationUnit generateDTOJavaFile(IntrospectedTable iIntrospectedTable, String iDTOClazzBasePackage) {
        String iEntityClazz = iIntrospectedTable.getBaseRecordType();
        String iModel = iIntrospectedTable.getFullyQualifiedTable().getDomainObjectName();
        String iDTO = StringUtils.easyAppend("{}DTO", iModel);
        String iDTOClazz = StringUtils.easyAppend("{}.{}", iDTOClazzBasePackage, iDTO);
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(iDTOClazz);
        return generateDTOJavaFile(iIntrospectedTable, type);
    }

    public static CompilationUnit generateDTOJavaFile(IntrospectedTable iIntrospectedTable, FullyQualifiedJavaType iDTOType) {
        Set<FullyQualifiedJavaType> imports = new HashSet<FullyQualifiedJavaType>();
        String iEntityClazz = iIntrospectedTable.getBaseRecordType();
        String iModel = iIntrospectedTable.getFullyQualifiedTable().getDomainObjectName();
        String iDTO = StringUtils.easyAppend("{}DTO", iModel);

        TopLevelClass tlc = new TopLevelClass(iDTOType);
        tlc.setVisibility(JavaVisibility.PUBLIC);
        tlc.addImportedType(new FullyQualifiedJavaType(iIntrospectedTable.getBaseRecordType()));
        List<IntrospectedColumn> columns = iIntrospectedTable.getAllColumns();

        for(IntrospectedColumn column : columns){
            Field field = FieldUtils.generatePropertyField(column);
            tlc.addField(field);
            tlc.addMethod(MethodUtils.generateGetMethod(field));
            tlc.addMethod(MethodUtils.generateSetMethod(field));
            imports.add(column.getFullyQualifiedJavaType());
        }


        {
            Method method = new Method("parser");
            Parameter parameter = new Parameter(new FullyQualifiedJavaType(iEntityClazz), "obj");
            method.addParameter(parameter);
            method.setStatic(true);
            method.setVisibility(JavaVisibility.PUBLIC);
            method.setReturnType(iDTOType);
            method.addBodyLine(StringUtils.easyAppend("{} dto = DataUtils.copyPropertiesIgnoreNull(obj, {}.class);", iDTO, iDTO));
            method.addBodyLine("return dto;");
            method.addException(new FullyQualifiedJavaType(IOException.class.getName()));
            tlc.addMethod(method);
        }
        {
            Method method = new Method("parser");
            FullyQualifiedJavaType pFullyQualifiedJavaType = new FullyQualifiedJavaType(List.class.getName());
            pFullyQualifiedJavaType.addTypeArgument(new FullyQualifiedJavaType(iEntityClazz));
            Parameter parameter = new Parameter(pFullyQualifiedJavaType, "objs");
            method.addParameter(parameter);
            method.setStatic(true);
            method.setVisibility(JavaVisibility.PUBLIC);
            FullyQualifiedJavaType rFullyQualifiedJavaType = new FullyQualifiedJavaType(List.class.getName());
            rFullyQualifiedJavaType.addTypeArgument(iDTOType);
            method.setReturnType(rFullyQualifiedJavaType);
            method.addBodyLine(StringUtils.easyAppend("List<{}> arrays = new ArrayList<>();", iDTO));
            method.addBodyLine(StringUtils.easyAppend("if(objs instanceof PageList) {", iDTO));
            method.addBodyLine(StringUtils.easyAppend("\tarrays = new PageList<{}>(((PageList) objs).getPaginator());", iDTO));
            method.addBodyLine(StringUtils.easyAppend("}"));
            method.addBodyLine(StringUtils.easyAppend("for({} obj : objs) {", iModel));
            method.addBodyLine(StringUtils.easyAppend("\tarrays.add(parser(obj));"));
            method.addBodyLine(StringUtils.easyAppend("}"));
            method.addBodyLine("return arrays;");
            method.addException(new FullyQualifiedJavaType(IOException.class.getName()));
            tlc.addMethod(method);
        }

        imports.add(new FullyQualifiedJavaType(PageList.class.getName()));
        imports.add(new FullyQualifiedJavaType(DataUtils.class.getName()));
        imports.add(new FullyQualifiedJavaType(PageList.class.getName()));
        imports.add(new FullyQualifiedJavaType(IOException.class.getName()));
        imports.add(new FullyQualifiedJavaType(ArrayList.class.getName()));
        imports.add(new FullyQualifiedJavaType(Date.class.getName()));
        imports.add(new FullyQualifiedJavaType(List.class.getName()));
        tlc.addImportedTypes(imports);

        return tlc;
    }
}
