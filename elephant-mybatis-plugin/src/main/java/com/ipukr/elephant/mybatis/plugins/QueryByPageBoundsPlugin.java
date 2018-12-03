package com.ipukr.elephant.mybatis.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wmw on 5/12/16.
 */
@Deprecated
public class QueryByPageBoundsPlugin extends PluginAdapter {

    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        Method iMethod = new Method();

        iMethod.setName("query");
        iMethod.setVisibility(method.getVisibility());


        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(List.class.getName());
        returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        iMethod.setReturnType(returnType);


        iMethod.getParameters().clear();
        iMethod.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()), "record", "@Param(\"record\")"));
        iMethod.addParameter(new Parameter(new FullyQualifiedJavaType("PageBounds"), "bounds"));

        interfaze.addMethod(iMethod);

        Set set = new HashSet<FullyQualifiedJavaType>();
        set.add(FullyQualifiedJavaType.getNewListInstance());
        set.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
        set.add(new FullyQualifiedJavaType("com.github.miemiedev.mybatis.paginator.domain.PageBounds"));
        interfaze.addImportedTypes(set);
        return true;
    }
}
