package com.ipukr.elephant.mybatis.plugins;

import com.ipukr.elephant.mybatis.plugins.utils.FieldUtils;
import com.ipukr.elephant.mybatis.plugins.utils.MethodUtils;
import com.ipukr.elephant.mybatis.plugins.utils.ModelUtils;
import com.ipukr.elephant.utils.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.TableConfiguration;

import java.util.*;

/**
 * 属性拼接 / 追加属性
 *
 * Created by ryan on 上午3:23.
 */
public class ModelAppendPlugin extends PluginAdapter {

    public static final String MODELS_NAME = "Models";

    public static final String Lists_NAME = "Lists";

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }


    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String table =  introspectedTable.getTableConfiguration().getTableName();

        if(table.matches(".*%.*")) {
            return true;
        } else {
            // 实体
            String modelAppend = introspectedTable.getTableConfigurationProperty(MODELS_NAME);
            if(modelAppend!=null && !modelAppend.equals("")) {
                String[] items = modelAppend.split(",");
                for(String item : items) {

                    String domain = item.split(":")[0];
                    String name = item.split(":").length>1?item.split(":")[1]:"m"+domain;

                    Field mfield = new Field();
                    mfield.setVisibility(JavaVisibility.PRIVATE);

                    FullyQualifiedJavaType dFullyQualifiedJavaType = new FullyQualifiedJavaType(domain);
                    mfield.setType(dFullyQualifiedJavaType);

                    mfield.setName(name);
                    topLevelClass.addField(mfield);

                    // Setter
                    {
                        Method method = new Method();
                        method.setVisibility(JavaVisibility.PUBLIC);

                        String setterName = StringUtils.easyAppend("set{}{}",
                                name.substring(0, 1).toUpperCase(),
                                name.substring(1));

                        method.setName(setterName);

                        method.addParameter(new Parameter(dFullyQualifiedJavaType, ModelUtils.getDomainName(mfield.getName())));
                        method.addBodyLine("this." + mfield.getName() + "=" + ModelUtils.getDomainName(mfield.getName()) + ";");
                        topLevelClass.addMethod(method);
                    }

                    // Getter
                    {
                        Method method = new Method();
                        method.setVisibility(JavaVisibility.PUBLIC);
                        method.setReturnType(dFullyQualifiedJavaType);
                        String getterName = StringUtils.easyAppend("get{}{}",
                                name.substring(0, 1).toUpperCase(),
                                name.substring(1));
                        method.setName(getterName);

                        method.addBodyLine("return this." + mfield.getName()  + ";");
                        topLevelClass.addMethod(method);
                    }
                }
            }
            // List<实体>
            String modelAppendList = introspectedTable.getTableConfigurationProperty(Lists_NAME);
            if(modelAppendList!=null && !modelAppendList.equals("")) {
                String[] items = modelAppendList.split(",");
                for(String item : items) {

                    String domain = item.split(":")[0];
                    String name = item.split(":").length>1?item.split(":")[1]:"m"+domain;

                    Field mfield = new Field();
                    mfield.setVisibility(JavaVisibility.PRIVATE);

                    FullyQualifiedJavaType dFullyQualifiedJavaType = new FullyQualifiedJavaType(List.class.getName());
                    dFullyQualifiedJavaType.addTypeArgument(new FullyQualifiedJavaType(domain));

                    mfield.setType(dFullyQualifiedJavaType);
                    mfield.setName(name);
                    mfield.setInitializationString("new ArrayList()");

                    topLevelClass.addField(mfield);

                    // Setter
                    {
                        Method method = new Method();
                        method.setVisibility(JavaVisibility.PUBLIC);


                        String setterName = StringUtils.easyAppend("set{}{}",
                                name.substring(0, 1).toUpperCase(),
                                name.substring(1));

                        method.setName(setterName);

                        method.addParameter(new Parameter(dFullyQualifiedJavaType, ModelUtils.getDomainName(mfield.getName())));
                        method.addBodyLine("this." + mfield.getName() + "=" + ModelUtils.getDomainName(mfield.getName()) + ";");
                        topLevelClass.addMethod(method);
                    }

                    // Getter
                    {
                        Method method = new Method();
                        method.setVisibility(JavaVisibility.PUBLIC);
                        method.setReturnType(dFullyQualifiedJavaType);
                        String getterName = StringUtils.easyAppend("get{}{}",
                                name.substring(0, 1).toUpperCase(),
                                name.substring(1));
                        method.setName(getterName);

                        method.addBodyLine("return this." + mfield.getName()  + ";");
                        topLevelClass.addMethod(method);
                    }
                }

                Set set = new HashSet<FullyQualifiedJavaType>();
                set.add(new FullyQualifiedJavaType(List.class.getName()));
                set.add(new FullyQualifiedJavaType(ArrayList.class.getName()));
                topLevelClass.addImportedTypes(set);
            }

            String modelImport = introspectedTable.getTableConfigurationProperty("modelImport");
            if(modelImport!=null && !modelImport.equals("")) {
                String[] items = modelImport.split(",");
                Set set = new HashSet<FullyQualifiedJavaType>();
                for(String item : items) {
                    set.add(new FullyQualifiedJavaType(item));
                }
                topLevelClass.addImportedTypes(set);
            }

            return true;
        }
    }
}
