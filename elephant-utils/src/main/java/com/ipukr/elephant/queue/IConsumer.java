package com.ipukr.elephant.queue;

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
     * @return
     */
    T consume();
}
