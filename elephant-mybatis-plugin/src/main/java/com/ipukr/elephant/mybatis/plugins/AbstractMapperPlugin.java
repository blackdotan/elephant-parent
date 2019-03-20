package com.ipukr.elephant.mybatis.plugins;

import com.ipukr.elephant.mybatis.plugins.utils.MyBatisUtilities;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

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

    /**
     * client 类继承 AbstractMapper
     * @param interfaze
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        // 拼接抽象接口类
        String iTargetPackage = MyBatisUtilities.getMapperPackage(context);
        String clazz = iTargetPackage.concat(".AbstractMapper");
        FullyQualifiedJavaType iAbstractMapperType = new FullyQualifiedJavaType(clazz);

        // 获取 AbstractMapper 实现
        iAbstractMapperType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));

        Iterator<IntrospectedColumn> it = introspectedTable.getPrimaryKeyColumns().iterator();
        boolean flag = false;
        if (it.hasNext()){
            flag = true;
            IntrospectedColumn iIntrospectedColumn = it.next();
            iAbstractMapperType.addTypeArgument(iIntrospectedColumn.getFullyQualifiedJavaType());
            if(it.hasNext()){
                flag = false;
            }
        }

        interfaze.addSuperInterface(iAbstractMapperType);
        interfaze.addImportedType(iAbstractMapperType);

        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }


    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {

        return super.contextGenerateAdditionalJavaFiles();
    }
}
