package com.ipukr.elephant.common;

/**
 * 枚举基础对象 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/6.
 */
public interface Identifiable<K> {
    /**
     * 获取枚举对象值
     *
     * @return 枚举主键
     */
    K getId();
}
