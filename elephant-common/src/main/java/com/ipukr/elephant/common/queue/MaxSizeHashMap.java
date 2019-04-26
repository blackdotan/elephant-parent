package com.ipukr.elephant.common.queue;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/4/14.
 */
public class MaxSizeHashMap<K, V> extends LinkedHashMap<K, V> {

    private final int maxSize;

    public MaxSizeHashMap(int maxSize) {
        this.maxSize = maxSize;
    }


    //
    //Returns true if this map should remove its eldest entry.
    //This method is invoked by put and putAll after inserting a new entry into the map.
    //It provides the implementor with the opportunity to remove the eldest entry each time a new
    //one is added. This is useful if the map represents a cache:
    //    it allows the map to reduce memory consumption by deleting stale entries.
    //
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxSize;
    }

}
