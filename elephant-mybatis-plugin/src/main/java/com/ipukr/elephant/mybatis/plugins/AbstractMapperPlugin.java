package com.ipukr.elephant.mybatis.plugins;

//import com.pukr.artchetype.template.api.AbstractMapper;
import com.ipukr.elephant.common.template.AbstractMapper;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;

import java.util.Iterator;
import java.util.List;

/**
 * Created by wmw on 1/7/17.
 */
public class AbstractMapperPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    @Deprecated
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(AbstractMapper.class.getName());
        type.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));

        Iterator<IntrospectedColumn> it = introspectedTable.getPrimaryKeyColumns().iterator();
        while (it.hasNext()){
            IntrospectedColumn iIntrospectedColumn = it.next();
            type.addTypeArgument(new FullyQualifiedJavaType(iIntrospectedColumn.getFullyQualifiedJavaType().getFullyQualifiedName()));
            if(it.hasNext()){
//                throw new RuntimeException("AbstractMapperPlugin 不支持多主键类型");
            }
        }

        FullyQualifiedJavaType supper = new FullyQualifiedJavaType(type.getFullyQualifiedName());
        interfaze.addSuperInterface(supper);

        interfaze.addImportedType(new FullyQualifiedJavaType(AbstractMapper.class.getName()));

        return true;
    }
}
