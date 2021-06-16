package com.blackdotan.elephant.common.queue;

import java.util.concurrent.Callable;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2018/1/2.
 */
public interface IConsumer<T> extends Callable {
    /**
     * 消费
     * @return 队列元素
     */
    T consume();
}
