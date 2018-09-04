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

    /**
     * 设置实例列表
     *
     * @param buffer 设置随机数组
     */
    public void setBuffer(List<T> buffer) {
        buffer = buffer;
    }

    /**
     * 新增实例
     * @param instance 新增实例
     */
    public void add(T instance) {
        buffer.add(instance);
    }

    /**
     * 随机一条记录
     *
     * @return 泛型实例
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
     * @param n 随机条数
     * @return 数组
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
