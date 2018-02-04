package com.ipukr.elephant.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * 数据随机类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/14.
 */
public class BufferDataRandom <T> {

    List<T> buffer = new ArrayList<T>();

    /**
     * 创建对象
     *
     * @param buffer 随机的数组
     *
     */
    public BufferDataRandom(List<T> buffer) {
        this.buffer = buffer;
    }

    public void setBuffer(List<T> buffer) {
        buffer = buffer;
    }

    /**
     *
     * @param instance
     */
    public void add(T instance) {
        buffer.add(instance);
    }

    /**
     * 随机一条记录
     *
     * @return
     */
    public T random() {
        if(!buffer.isEmpty()) {
            return (T) buffer.get(new Random().nextInt(buffer.size()));
        }
        return null;
    }

    /**
     * 随机n条记录
     *
     * 不重复, 重复判断方式为是否equals
     *
     * @param n
     * @return
     */
    public List<T> random(int n) {
        List<T> array = new ArrayList<>();
        int count = 0;
        int max = Math.min(n, buffer.size());

        while (count < max - 1){
            T instance = random();
            Iterator<T> it = array.iterator();
            if(!it.hasNext()) {
                array.add(instance);
            } else {
                boolean bool = false;
                while (it.hasNext()) {
                    if(it.next().equals(instance)) {
                        bool = true;
                    }
                }
                if (!bool) {
                    array.add(instance);
                    count++;
                }
            }
        }
        return array;
    }
}
