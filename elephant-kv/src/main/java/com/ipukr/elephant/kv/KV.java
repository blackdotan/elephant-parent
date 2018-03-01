package com.ipukr.elephant.kv;

import java.io.IOException;
import java.util.List;

/**
 * Created by ryan on 下午5:46.
 */
public interface KV<T> {

    /**
     * 新增键值
     *
     * @param key 键
     * @param value 值
     */
    void add(String key, T value);

    /**
     * 新增键值
     *
     * @param key 键
     * @param value 值
     * @param ttl 生命周期
     */
    void add(String key, T value, Long ttl);

    /**
     * 获取键值
     * @param key 键
     * @return 值
     * @throws IOException 异常
     */
    T get(String key) throws IOException;

    /**
     * 获取键值
     * @param key 键
     * @return 值
     * @throws IOException 异常
     */
    T get(String key, T def) throws IOException;

    /**
     * 删除键值
     * @param key 键
     */
    void del(String key);

    /**
     * 判断键值是否存在
     *
     * @param key 键
     * @return 值
     */
    boolean exist(String key);

    /**
     * 判断键值是否存在
     *
     * @param key
     * @return 是否存在
     */
    boolean contain(String key);

    /**
     * 获取所有值
     * @return 所有元素
     * @throws Exception 异常
     */
    List<T> all() throws Exception;

    /**
     * @param pattern
     * @return
     * @throws IOException
     */
    List<T> match(String pattern) throws IOException;

    void put(String key, T value);

    void put(String key, T value, Long ttl);

    T pop(String key) throws IOException;

    T pop(String key, T def) throws IOException;

}
