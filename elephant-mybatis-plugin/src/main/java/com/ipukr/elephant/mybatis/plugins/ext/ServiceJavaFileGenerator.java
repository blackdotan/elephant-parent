package com.ipukr.elephant.mybatis.plugins.ext;

import com.ipukr.elephant.common.template.InterfaceService;
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
public class ServiceJavaFileGenerator extends AbstractJavaGenerator {

    public static final String BASE_PACKAGE = "BasePackage";

    private Context context;
    private Properties properties;
    private IntrospectedTable introspectedTable;

    private String iModel;
    private String iBasePackage;

    public ServiceJavaFileGenerator(Context context, IntrospectedTable introspectedTable, Properties properties) {
        this.context = context;
        this.introspectedTable = introspectedTable;
        this.properties = properties;

        iBasePackage = properties.getProperty(BASE_PACKAGE);
        iModel = introspectedTable.getFullyQualifiedTable().getDomainObjectName();

        setContext(this.context);
        setIntrospectedTable(this.introspectedTable);
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        /**
         * 获取常量
         * */


        // 拼接 包名 + 类名
        String clazz = StringUtils.easyAppend("{}.service.{}Service", iBasePackage, iModel);
        context.getJdbcConnectionConfiguration().getConnectionURL();

        String PrimaryType = "String";
        Iterator<IntrospectedColumn> it = introspectedTable.getPrimaryKeyColumns().iterator();
        if(it.hasNext()){
            PrimaryType = it.next().getFullyQualifiedJavaType().getShortName();
        }

        /**
         * 生成Service类
         * */
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(clazz);


        Interface tlc = new Interface(type);
        tlc.setVisibility(JavaVisibility.PUBLIC);

        // 继承抽象接口
        FullyQualifiedJavaType supperClass = new FullyQualifiedJavaType("InterfaceService");
        supperClass.addTypeArgument(new FullyQualifiedJavaType(StringUtils.easyAppend("{}", iModel)));
        supperClass.addTypeArgument(new FullyQualifiedJavaType(PrimaryType));

        tlc.addSuperInterface(supperClass);

        /**
         * 依赖导入
         * */
        Set<FullyQualifiedJavaType> imports = new HashSet<FullyQualifiedJavaType>();
        imports.add(new FullyQualifiedJavaType(InterfaceService.class.getCanonicalName()));
        imports.add(new FullyQualifiedJavaType(StringUtils.easyAppend("{}.entity.{}", iBasePackage, iModel)));
        tlc.addImportedTypes(imports);

        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        answer.add(tlc);
        return answer;
    }
}
