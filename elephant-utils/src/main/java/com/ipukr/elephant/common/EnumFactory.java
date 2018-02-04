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
     * @param type
     * @param id
     * @param <T>
     * @param <K>
     * @return
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
     * @param type
     * @param id
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T extends Enum<T> & Identifiable<K> , K > T findAccordingPropName(Class<T> type, K id) {
        EnumSet<T> set = EnumSet.allOf(type);
        if(set == null || set.size() <= 0){
            String error = StringUtils.easyAppend("Not Found Enum {} Of Value :", type.getSimpleName(), id);
            throw new IllegalArgumentException(error);
        }
        for(T t: set){
            if(t.getId().equals(id)){
                return t;
            }
        }
        String error = StringUtils.easyAppend("Not Found Enum {} Of Value :", type.getSimpleName(), id);
        throw new IllegalArgumentException(error);
    }
}