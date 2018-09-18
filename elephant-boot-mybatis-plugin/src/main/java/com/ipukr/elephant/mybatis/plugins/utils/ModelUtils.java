package com.ipukr.elephant.mybatis.plugins.utils;

import com.ipukr.elephant.utils.StringUtils;

/**
 * Created by ryan on 下午2:03.
 */
public class ModelUtils {

    /**
     * 首字母小写
     * @param domain
     * @return
     */
    public static String getDomainName(String domain){
        return StringUtils.easyAppend("{}{}", domain.substring(0, 1).toLowerCase(), domain.substring(1));
    }
}
