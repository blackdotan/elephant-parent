package com.ipukr.elephant.mybatis.plugins.ext;


import com.ipukr.elephant.common.template.AbstractMapper;
import com.ipukr.elephant.common.template.AbstractService;
import com.ipukr.elephant.mybatis.plugins.utils.MethodUtils;
import com.ipukr.elephant.utils.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.config.Context;

import java.util.*;

/**
 * Created by wmw on 1/13/17.
 */
public class ServiceImplJavaFileGenerator extends AbstractJavaGenerator {

    public static final String BASE_PACKAGE = "BasePackage";
//    public static final String PLACE_HOLDER_BASESERVICEIMPLPACKAGE = "BaseServiceImplPackage";

    private Context context;
    private Properties properties;
    private IntrospectedTable introspectedTable;


    public ServiceImplJavaFileGenerator(Context context, IntrospectedTable introspectedTable, Properties properties) {
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
        String iBasePackage = properties.getProperty(BASE_PACKAGE);

        String targetPackage = context.getJavaModelGeneratorConfiguration().getTargetPackage();
        String mapperPackage = context.getJavaClientGeneratorConfiguration().getTargetPackage();
        String Model = introspectedTable.getFullyQualifiedTable().getDomainObjectName();

        String clazz = StringUtils.easyAppend("{}.service.impl.{}ServiceImpl", iBasePackage, Model);

        String PrimaryType = "String";
        Iterator<IntrospectedColumn> it = introspectedTable.getPrimaryKeyColumns().iterator();
        if(it.hasNext()){
            PrimaryType = it.next().getFullyQualifiedJavaType().getShortName();
        }


        /**
         *
         * 生成ServiceImpl类
         *
         * */
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(clazz);
        TopLevelClass tlc = new TopLevelClass(type);
        tlc.setVisibility(JavaVisibility.PUBLIC);
        tlc.addAnnotation("@Service");

        // 继承抽象类
        FullyQualifiedJavaType supperClass = new FullyQualifiedJavaType("AbstractService");
        supperClass.addTypeArgument(new FullyQualifiedJavaType(StringUtils.easyAppend("{}.{}", targetPackage, Model)));
        supperClass.addTypeArgument(new FullyQualifiedJavaType(PrimaryType));
        tlc.setSuperClass(supperClass);


        // 继承抽象类
        FullyQualifiedJavaType serviceClass = new FullyQualifiedJavaType(StringUtils.easyAppend("{}.service.{}Service", iBasePackage, Model));
        tlc.addSuperInterface(serviceClass);

        /**
         * 实现方法
         * */
        tlc.addMethod(getMapper(Model));
        tlc.addMethod(insert(Model));

        /**
         * 类属性定义
         * */
        FullyQualifiedJavaType mapper = new FullyQualifiedJavaType(StringUtils.easyAppend("{}.{}Mapper",mapperPackage, Model));
        Field field = new Field("m{Model}Mapper".replace("{Model}",Model), mapper);
        field.addAnnotation("@Resource");
        field.setVisibility(JavaVisibility.PRIVATE);
        tlc.addField(field);


        /**
         * 依赖导入
         * */
        Set<FullyQualifiedJavaType> imports = new HashSet<FullyQualifiedJavaType>();
        imports.add(new FullyQualifiedJavaType("javax.annotation.Resource"));
        imports.add(new FullyQualifiedJavaType(AbstractMapper.class.getName()));
        imports.add(new FullyQualifiedJavaType(AbstractService.class.getName()));
        imports.add(new FullyQualifiedJavaType("org.springframework.stereotype.Service"));
        imports.add(new FullyQualifiedJavaType("java.lang.Exception"));
        imports.add(new FullyQualifiedJavaType("com.ipukr.elephant.utils.StringUtils"));
        imports.add(new FullyQualifiedJavaType(StringUtils.easyAppend("{}.{}", targetPackage, Model)));
        imports.add(new FullyQualifiedJavaType(StringUtils.easyAppend("{}.service.{}Service", iBasePackage, Model)));
        imports.add(new FullyQualifiedJavaType(StringUtils.easyAppend("{}.{}Mapper", mapperPackage, Model)));
        tlc.addImportedTypes(imports);


        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        answer.add(tlc);
        return answer;
    }

    /**
     *  @Override
     *  public AbstractMapper getMapper() {
     *      return null;
     *  }
     * @return
     */
    private Method getMapper(String Model){
        Method method = new Method();
        method.setName("getMapper");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addAnnotation(MethodUtils.annotation("This Method Return Mapper Object As Super CRUD Method Access DataBase"));
        method.addAnnotation("@Override");
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(AbstractMapper.class.getCanonicalName());
        method.setReturnType(returnType);

        method.addBodyLine("return m{Model}Mapper;".replace("{Model}", Model));
        return method;
    }

    public Method insert(String Model){
        Method method = new Method();
        method.setName("insert");
        method.setVisibility(JavaVisibility.PUBLIC);

        method.addAnnotation(MethodUtils.annotation("This Is Insert Method ReImplement, Please The Logistics Of Check Insert Operation is　Legal"));
        method.addAnnotation("@Override");
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("boolean");
        method.setReturnType(returnType);
        method.addException(new FullyQualifiedJavaType("Exception"));

        Parameter p1 = new Parameter(new FullyQualifiedJavaType(Model), "instance");
        method.addParameter(p1);

        StringBuffer condition = new StringBuffer(" 1 != 1 ||");
        for(IntrospectedColumn column : introspectedTable.getAllColumns()){
            if(!column.isNullable()){
                String getterName = StringUtils.easyAppend("get{}{}",
                        column.getJavaProperty().substring(0,1).toUpperCase(),
                        column.getJavaProperty().substring(1));
                condition.append("instance.{}() == null ||".replace("{}", getterName));
                if(column.getFullyQualifiedJavaType().getShortName().equals("String")){
                    condition.append("instance.{}().equals(\"\") ||"
                            .replace("{}", getterName));

                    condition.append("instance.{}().length()>{LEN} ||"
                            .replace("{}", getterName)
                            .replace("{LEN}", String.valueOf(column.getLength()) ));
                }
            }
        }
        int loc = condition.lastIndexOf("|");
        if(loc!=-1){
            condition.delete(loc-1 , loc+1);
        }

        StringBuffer body = new StringBuffer();
        body.append("if({}){\n".replace("{}",condition))
                .append("\t\t\tString error = StringUtils.easyAppend(\"【{Model}Service】:新增数据异常, instance={}\" ,instance.toString());\n".replace("{Model}", Model))
                .append("\t\t\tthrow new RuntimeException(error);\n")
                .append("\t\t}\n")
                .append("\t\treturn m{Model}Mapper.insert(instance) > 0;\n".replace("{Model}", Model));
        method.addBodyLine(body.toString());

        return method;
    }


}
