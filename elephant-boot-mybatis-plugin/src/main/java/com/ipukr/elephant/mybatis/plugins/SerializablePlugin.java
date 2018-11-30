package com.ipukr.elephant.mybatis.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * 实体类继承序列化
 *
 * Created by wmw on 16/10/21.
 */
public class SerializablePlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType clazz = new FullyQualifiedJavaType(java.io.Serializable.class.getName());
        topLevelClass.addImportedType(clazz);
        topLevelClass.addSuperInterface(clazz);
        return true;
    }

}
