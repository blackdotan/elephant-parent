package com.ipukr.elephant.common;

import com.ipukr.elephant.utils.StringUtils;

import java.util.EnumSet;

/**
 * 枚举对象处理工厂类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/6.
 */
public class EnumFactory {
    /**
     * 根据[数据类型], [枚举名]获取枚举对象
     *
     * @param type 枚举类型.class文件
     * @param id getId
     * @param <T> 枚举泛型
     * @param <K> 枚举getId类型
     * @return 枚举对象
     */
    public static <T extends Enum<T> & Identifiable<K> , K > T findAccordingValue(Class<T> type, K id) {
        for (T t : type.getEnumConstants()) {
            if(t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    /**
     * 根据[数据类型], [枚举值]获取枚举对象
     *
     * @param type 枚举类型.class文件
     * @param name 属性名
     * @param <T> 枚举泛型
     * @param <K> 枚举getId类型
     * @return 枚举对象
     */
    public static <T extends Enum<T> & Identifiable , K > T findAccordingPropName(Class<T> type, K name) {
        EnumSet<T> set = EnumSet.allOf(type);
        if(set == null || set.size() <= 0){
            String error = StringUtils.easyAppend("Not Found Enum {} Of Value :", type.getSimpleName(), name);
            throw new IllegalArgumentException(error);
        }
        for(T t: set){
            if(t.name().equals(name)){
                return t;
            }
        }
        String error = StringUtils.easyAppend("Not Found Enum {} Of Value :", type.getSimpleName(), name);
        throw new IllegalArgumentException(error);
    }
}