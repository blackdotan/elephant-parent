package com.ipukr.elephant.architecture;

import java.util.Map;

/**
 * 接口示例 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/19.
 */
public interface KV {
    /**
     * 新增Key-Value
     * @param key
     * @param value
     */
    void add(String key, Object value);

    /**
     * 删除Key
     * @param key
     */
    void remove(String key);

    /**
     * 判断Key是否存在
     * @param key
     */
    boolean exists(String key);

    /**
     * Key模糊匹配
     * @param pattern
     * @return
     */
    Map<String, Object> match(String pattern);
}
