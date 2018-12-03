package com.ipukr.elephant.mybatis.plugins;

import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/7/30.
 */
public class StateToEnumHandler extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }


}
