package com.ipukr.elephant.mybatis.plugins.utils;

//import com.google.common.collect.Sets;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.ModelType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ryan on 上午11:30.
 */
public class FieldUtils {

    public static void addField(TopLevelClass tTopLevelClass, String domain) {
        Field mfield = new Field();
        mfield.setVisibility(JavaVisibility.PRIVATE);
        mfield.setType(new FullyQualifiedJavaType(domain));

        mfield.setName("m"+domain);
        tTopLevelClass.addField(mfield);
    }


    /**
     * @param introspectedTable
     * @param label
     * @return
     */
    public static List<IntrospectedColumn> uniqueColumn(IntrospectedTable introspectedTable, String label) {
        List<IntrospectedColumn> columnArr = new ArrayList<IntrospectedColumn>();
        String uniqueArray = introspectedTable.getTableConfigurationProperty(label);
        if(uniqueArray!=null && !uniqueArray.trim().equals("")) {
            Set<String> sets = new HashSet<>();
            for(String item : uniqueArray.split(",")) {
                sets.add(item);
            }
            for (IntrospectedColumn column : introspectedTable.getBaseColumns()) {
                if (sets.contains(column.getActualColumnName())) {
                    columnArr.add(column);
                }
            }
        }
        return columnArr;
    }

    public static Field generatePropertyField(IntrospectedColumn column) {
        Field field = new Field();
        field.setName(column.getJavaProperty());
        field.setType(column.getFullyQualifiedJavaType());
        field.setVisibility(JavaVisibility.PRIVATE);
        return field;
    }
}
