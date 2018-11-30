package com.ipukr.elephant.common.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ryan on 上午3:52.
 */
public class ThreadFactory {

    // 单例模式
    private static ThreadFactory instance = null;
    // 获取处理器数目
    private final Integer numberOfCore = Runtime.getRuntime().availableProcessors();
    // 线程池
    private ExecutorService pool = null;
    // 单例模式
    private ThreadFactory() {}

    /**
     * 获取线程池帮助类,
     * @return
     */
    public synchronized static ThreadFactory getInstance(){
        if(instance == null){
            instance = new ThreadFactory();
        }
        return instance;
    }

    /**
     * 获取线程池
     * @return
     */
    public ExecutorService getPool(){
        if(pool == null){
            pool = Executors.newFixedThreadPool(numberOfCore);
        }
        return pool;
    }
}
