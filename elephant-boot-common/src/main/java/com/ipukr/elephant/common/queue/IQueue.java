package com.ipukr.elephant.common.queue;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2018/1/2.
 */
public interface IQueue<T> {
    /**
     * 进栈
     * @param element 泛型T实例
     */
    void push(T element);

    /**
     * 出栈
     * @return 泛型T实例
     */
    T pop();

    /**
     * 清空栈
     */
    void clear();

    /**
     * 判断栈是否为空
     * @return bool
     */
    boolean isEmpty();

}
