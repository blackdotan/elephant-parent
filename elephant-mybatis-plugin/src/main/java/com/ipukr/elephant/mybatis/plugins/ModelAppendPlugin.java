package com.ipukr.elephant.mybatis.plugins;

import com.ipukr.elephant.mybatis.plugins.utils.ColumnUtils;
import com.ipukr.elephant.mybatis.plugins.utils.MethodUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 属性拼接 / 追加属性
 *
 * Created by ryan on 上午3:23.
 */
public class ModelAppendPlugin extends PluginAdapter {

    private static Logger logger = LoggerFactory.getLogger(ModelAppendPlugin.class);

    public static final String MODELS_NAME = "Models";

    public static final String ASSOCIATIONS_NAME = "Associations";

    public static final String Lists_NAME = "Lists";

    public static final String COLLECTIONS_NAME = "Collections";

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean sqlMapResultMapWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        super.sqlMapResultMapWithoutBLOBsElementGenerated(element, introspectedTable);

        {
            // Collections
            String collections = introspectedTable.getTableConfigurationProperty(COLLECTIONS_NAME);
            if (collections != null && !collections.equals("")) {
                List<IntrospectedColumn> columns = introspectedTable.getPrimaryKeyColumns();
                if (columns.size()>0) {
                    // Column 属性
                    StringBuffer columnAttr = new StringBuffer();
                    // Select 属性 尾部
                    StringBuffer selectAttrSuffix = new StringBuffer();
                    for (IntrospectedColumn column : columns) {
                        columnAttr.append(ColumnUtils.retActualColumnName(column)).append(",");
                        selectAttrSuffix.append(ColumnUtils.retClazzNameFormat(column));
                    }
                    columnAttr.deleteCharAt(columnAttr.length()-1);
                    String[] items = collections.split(",");
                    for (String item : items) {
                        String entity = item.split(":")[0];
                        String name = item.split(":").length > 1 ? item.split(":")[1] : "m" + entity;

                        FullyQualifiedJavaType dFullyQualifiedJavaType = new FullyQualifiedJavaType(entity);
                        XmlElement ele = new XmlElement("collection");
                        ele.addAttribute(new Attribute("property", name));
                        ele.addAttribute(new Attribute("ofType", dFullyQualifiedJavaType.getFullyQualifiedName()));
                        ele.addAttribute(new Attribute("javaType", "java.util.ArrayList"));

                        // Column 属性
                        ele.addAttribute(new Attribute("column", columnAttr.toString()));

                        // Select 属性
                        String pck = introspectedTable.getContext().getJavaClientGeneratorConfiguration().getTargetPackage();
                        StringBuffer buffer = new StringBuffer();
                        buffer.append(pck).append(".").append(dFullyQualifiedJavaType.getShortName()).append("Mapper");
                        buffer.append(".findCollection")
                                .append(dFullyQualifiedJavaType.getShortName())
                                .append("By")
                                .append(selectAttrSuffix.toString());
                        ele.addAttribute(new Attribute("select", buffer.toString()));

                        element.addElement(ele);
                    }
                } else {
                    logger.warn("{} 未找到主键字段，不支持无主键生成Mapper:collection",introspectedTable.getTableConfiguration().getTableName());
                }
            }
        }


        {
            // Associations
            String associations = introspectedTable.getTableConfigurationProperty(ASSOCIATIONS_NAME);
            if (associations != null && !associations.equals("")) {
                List<IntrospectedColumn> columns = introspectedTable.getPrimaryKeyColumns();
                if(columns.size()>0) {
                    // Column 属性
                    StringBuffer columnAttr = new StringBuffer();
                    // Select 属性 尾部
                    StringBuffer selectAttrSuffix = new StringBuffer();
                    for (IntrospectedColumn column : columns) {
                        columnAttr.append(ColumnUtils.retActualColumnName(column)).append(",");
                        selectAttrSuffix.append(ColumnUtils.retClazzNameFormat(column));
                    }
                    columnAttr.deleteCharAt(columnAttr.length()-1);

                    String[] items = associations.split(",");
                    for (String item : items) {
                        String entity = item.split(":")[0];
                        String name = item.split(":").length > 1 ? item.split(":")[1] : "m" + entity;

                        FullyQualifiedJavaType dFullyQualifiedJavaType = new FullyQualifiedJavaType(entity);
                        XmlElement ele = new XmlElement("association");
                        ele.addAttribute(new Attribute("property", name));
                        ele.addAttribute(new Attribute("javaType", dFullyQualifiedJavaType.getFullyQualifiedName()));
                        // Column 属性
                        ele.addAttribute(new Attribute("column", columnAttr.toString()));
                        // Select 属性
                        String pck = introspectedTable.getContext().getJavaClientGeneratorConfiguration().getTargetPackage();
                        StringBuffer buffer = new StringBuffer();
                        buffer.append(pck).append(".").append(dFullyQualifiedJavaType.getShortName()).append("Mapper");
                        buffer.append(".findAssociation")
                                .append(dFullyQualifiedJavaType.getShortName())
                                .append("By")
                                .append(selectAttrSuffix.toString());

                        ele.addAttribute(new Attribute("select", buffer.toString()));

                        element.addElement(ele);
                    }
                } else {
                    logger.warn("{} 未找到主键字段，不支持无主键生成Mapper:association",introspectedTable.getTableConfiguration().getTableName());
                }
            }
        }
        return true;
    }

    /**
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String table =  introspectedTable.getTableConfiguration().getTableName();

        if(table.matches(".*%.*")) {
            return true;
        } else {
            // Models
            appendAssociation(introspectedTable.getTableConfigurationProperty(MODELS_NAME), topLevelClass, introspectedTable);
            // Associations
            appendAssociation(introspectedTable.getTableConfigurationProperty(ASSOCIATIONS_NAME), topLevelClass, introspectedTable);
            // Lists
            appendCollection(introspectedTable.getTableConfigurationProperty(Lists_NAME), topLevelClass, introspectedTable);
            // Collections
            appendCollection(introspectedTable.getTableConfigurationProperty(COLLECTIONS_NAME), topLevelClass, introspectedTable);
            return true;
        }
    }

    /**
     * @param text
     * @param topLevelClass
     * @param introspectedTable
     */
    private void appendAssociation(String text, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if(text!=null && !text.equals("")) {
            String[] items = text.split(",");
            for(String item : items) {
                String domain = item.split(":")[0];
                String name = item.split(":").length>1?item.split(":")[1]:"m"+domain;

                Field mfield = new Field();
                mfield.setVisibility(JavaVisibility.PRIVATE);

                FullyQualifiedJavaType dFullyQualifiedJavaType = new FullyQualifiedJavaType(domain);
                mfield.setType(dFullyQualifiedJavaType);

                mfield.setName(name);
                topLevelClass.addField(mfield);
                topLevelClass.addMethod(MethodUtils.generateSetMethod(mfield));
                topLevelClass.addMethod(MethodUtils.generateGetMethod(mfield));
            }
        }
    }

    /**
     * @param text
     * @param topLevelClass
     * @param introspectedTable
     */
    private void appendCollection(String text, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if(text!=null && !text.equals("")) {
            String[] items = text.split(",");
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

                topLevelClass.addMethod(MethodUtils.generateSetMethod(mfield));
                topLevelClass.addMethod(MethodUtils.generateGetMethod(mfield));
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
    }


}
