package com.ipukr.elephant.kv;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by ryan on 下午5:46.
 */
public interface KV {

    /**
     * 新增键值
     *
     * @param key 键
     * @param value 值
     */
    void add(String key, byte[] value);

    /**
     * 新增键值
     *
     * @param key 键
     * @param value 值
     * @param ttl 生命周期
     */
    void add(String key, byte[] value, Long ttl);

    /**
     * 获取键值
     * @param key 键
     * @return 值
     * @throws IOException 异常
     */
    byte[] get(String key) throws IOException;

    /**
     * 获取键值
     * @param key 键
     * @return 值
     * @throws IOException 异常
     */
    byte[] get(String key, byte[] def) throws IOException;

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
     * @param pattern
     * @return
     * @throws IOException
     */
    List<byte[]> match(String pattern) throws IOException;

    /**
     * @param key
     * @param value
     */
    void put(String key, byte[] value);

    /**
     * @param key
     * @param value
     * @param ttl
     */
    void put(String key, byte[] value, Long ttl);

    /**
     * @param key
     * @return
     * @throws IOException
     */
    byte[] pop(String key) throws IOException;

    /**
     * @param key
     * @param def
     * @return
     * @throws IOException
     */
    byte[] pop(String key, byte[] def) throws IOException;

    /**
     * 获取所有Key
     * @return
     */
    Set<String> keys();
}
