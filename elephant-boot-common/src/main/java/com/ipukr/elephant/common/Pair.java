package com.ipukr.elephant.common;

/**
 * Created by wmw on 2/11/17.
 */
public class Pair<K, V> {
    private K k;
    private V v;

    public Pair(K k, V v){
        this.k = k;
        this.v = v;
    }

    /**
     * 获取键
     * @return 键
     */
    public K k(){
        return this.k;
    }

    /**
     * 获取值
     * @return 值
     */
    public V v(){
        return this.v;
    }

    /**
     * 生成键值对
     * @param k 键
     * @param v 值
     * @param <K> 键泛型
     * @param <V> 值泛型
     * @return
     */
    public static <K,V> Pair<K, V> of(K k, V v){
        return new Pair<K, V>(k,v);
    }
}
