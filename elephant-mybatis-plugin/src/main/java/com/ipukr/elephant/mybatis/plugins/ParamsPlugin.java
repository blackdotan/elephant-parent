package com.ipukr.elephant.mybatis.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wmw on 16/10/17.
 */
@Deprecated
public class ParamsPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }




    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        String name = "params";
        Field mfield = new Field();
        mfield.setVisibility(JavaVisibility.PRIVATE);
        Class<Map<Object,Object>> mapClass;
        Class cs = (Class<Map<Object, Object>>)(Class<?>)Map.class;
        mfield.setType(new FullyQualifiedJavaType(cs.getName()));
        mfield.setName("params");
        mfield.setInitializationString("new HashMap<Object,Object>()");

        topLevelClass.addField(mfield);

        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("setParams");
        method.addParameter(new Parameter(new FullyQualifiedJavaType(cs.getName()), name));
        method.addBodyLine("this." + name + "=" + name + ";");
        topLevelClass.addMethod(method);

        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);

        method.setReturnType(new FullyQualifiedJavaType(cs.getName()));
        method.setName("getParams");
        method.addBodyLine("return this." + name + ";");
        topLevelClass.addMethod(method);


        Set set = new HashSet<FullyQualifiedJavaType>();
        set.add(FullyQualifiedJavaType.getNewListInstance());
        set.add(new FullyQualifiedJavaType("java.util.Map"));
        set.add(new FullyQualifiedJavaType("java.util.HashMap"));
        topLevelClass.addImportedTypes(set);
        return true;
    }

}
