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

    public K k(){
        return this.k;
    }

    public V v(){
        return this.v;
    }

    public static <K,V> Pair<K, V> of(K k, V v){
        return new Pair<K, V>(k,v);
    }
}
