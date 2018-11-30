package com.ipukr.elephant.mybatis.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.List;
import java.util.StringTokenizer;

/**
 * 虚拟主键 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/11/28.
 */
public class VirtualPrimaryKeyPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        String virtualKey = introspectedTable.getTableConfiguration()
                .getProperty("VirtualKeyColumns");

        if (virtualKey != null) {
            StringTokenizer st = new StringTokenizer(virtualKey, ", ", false);
            while (st.hasMoreTokens()) {
                String column = st.nextToken();
                introspectedTable.addPrimaryKeyColumn(column);
            }
        }
    }
}
