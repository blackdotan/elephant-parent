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
     * @param key
     * @param value
     */
    void add(String key, T value);

    /**
     * 新增键值
     *
     * @param key
     * @param value
     * @param ttl
     */
    void add(String key, T value, Long ttl);

    /**
     * 获取键值
     * @param key
     * @return
     * @throws IOException
     */
    T get(String key) throws IOException;

    /**
     * 获取键值
     * @param key
     * @return
     * @throws IOException
     */
    T get(String key, T def) throws IOException;

    /**
     * 删除键值
     * @param key
     */
    void del(String key);

    /**
     * 判断键值是否存在
     *
     * @param key
     * @return
     */
    boolean exist(String key);

    /**
     * 判断键值是否存在
     *
     * @param key
     * @return
     */
    boolean contain(String key);

    List<T> all() throws Exception;

    List<T> match(String pattern) throws IOException;

    void put(String key, T value);

    void put(String key, T value, Long ttl);

    T pop(String key) throws IOException;

    T pop(String key, T def) throws IOException;

}
