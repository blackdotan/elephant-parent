package com.ipukr.elephant.kv.backend;

import com.ipukr.elephant.architecture.AbstractAPI;
import com.ipukr.elephant.architecture.context.Context;
import com.ipukr.elephant.kv.KV;

import java.util.*;

/**
 * Created by ryan on 下午5:47.
 */
public class MemoryKV<T> extends AbstractAPI<T> implements KV<T>{

    private Map<String, T> map = new HashMap();

    public MemoryKV(Context context, Class<T> generic) {
        super(context, generic);
    }

    /**
     * 新增
     * @param key
     * @param value
     */
    public void add(String key, T value) {
        map.put(key, value);
    }

    public List<T> all() throws Exception {
        List<T> arr = new ArrayList<T>();
        for(T t : map.values()) {
            arr.add(t);
        }
        return arr;
    }

    public T pop(String key) {
        return null;
    }

    @Override
    public void add(String key, T value, Long ttl) {
        map.put(key, value);
    }

    @Override
    public T get(String key) {
        return map.get(key);
    }

    @Override
    public T get(String key, T def) {
        return map.getOrDefault(key, def);
    }

    @Override
    public void del(String key) {
        map.remove(key);
    }

    @Override
    public boolean exist(String key) {
        return map.containsKey(key);
    }

    @Override
    public boolean contain(String key) {
        return map.containsKey(key);
    }

    @Override
    public List<T> match(String pattern) {
        List<T> arr = new ArrayList<T>();
        for(Map.Entry<String, T> entry : map.entrySet()) {
            if(entry.getKey().matches(pattern)) {
                arr.add(entry.getValue());
            }
        }
        return arr;
    }

    @Override
    public void put(String key, T value) {
        map.put(key, value);
    }

    @Override
    public void put(String key, T value, Long ttl) {
        map.put(key, value);
    }

    @Override
    public T pop(String key, T def) {
        return map.containsKey(key)?map.remove(key): def;
    }

    @Override
    public Set<String> keys() {
        return map.keySet();
    }
}
