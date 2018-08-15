package com.ipukr.elephant.common.queue;

import java.util.concurrent.Callable;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2018/1/2.
 */
public interface IProducer<T> extends Callable {
    /**
     * 生产
     * @param element 压栈袁术
     */
    void produce(T element);
}
