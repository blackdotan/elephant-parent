package com.ipukr.elephant.architecture.factory;

import com.ipukr.elephant.architecture.AbstractAPI;
import com.ipukr.elephant.architecture.configuration.Configuration;
import com.ipukr.elephant.architecture.configuration.PropertiesConfiguration;
import com.ipukr.elephant.architecture.constant.Constant;
import com.ipukr.elephant.architecture.context.Context;
import com.ipukr.elephant.architecture.pool.AbstractAPIPoolFactory;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;

/**
 * Created by ryan on 上午2:25.
 */
public class PoolFactory {

    private static final Logger logger = LoggerFactory.getLogger(com.ipukr.elephant.architecture.factory.PoolFactory.class);

    private static com.ipukr.elephant.architecture.factory.PoolFactory factory;

    private PoolFactory() {}

    /**
     * API 单例, 创建工厂
     *
     * @return
     */
    public synchronized static com.ipukr.elephant.architecture.factory.PoolFactory getInstance() {
        if(factory == null){
            factory = new com.ipukr.elephant.architecture.factory.PoolFactory();
        }
        return factory;
    }

    /**
     * 默认配置 config.properties 创建 接口类 instanceof AbstractAPI
     * @param <T>
     * @return
     * @throws ConfigurationException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws UnsupportedEncodingException
     */
    public <T> GenericObjectPool<AbstractAPI> build() throws Exception {
        String resource = com.ipukr.elephant.architecture.factory.Factory.class.getResource("/").getPath().concat(Constant.RESOURCE);
        resource = URLDecoder.decode(resource, "UTF-8");
        return build(resource);
    }


    /**
     * resource配置 创建 接口类 instanceof AbstractAPI
     *
     * @param resource
     * @param <T>
     * @return
     * @throws ConfigurationException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T> GenericObjectPool<T> build(String resource) throws Exception {
        Configuration configuration = new PropertiesConfiguration(resource);
        return build(configuration);
    }


    /**
     * configuration配置 创建 接口类 instanceof AbstractAPI
     *
     * @param configuration
     * @param <T>
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public <T> GenericObjectPool<T> build(Configuration configuration) throws Exception {
        return build(configuration, null, Object.class);

    }

    /**
     * 默认配置 config.properties 创建 接口类 instanceof AbstractAPI
     *
     * 携带 Class generic (服务于自定义API返回的数据类型)
     *
     * @param generic
     * @param <T>
     * @return
     * @throws ConfigurationException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws UnsupportedEncodingException
     */
    public <T, K> GenericObjectPool<T> build(Class<K> generic) throws Exception {
        String resource = Factory.class.getResource("/").getPath().concat(Constant.RESOURCE);
        resource = URLDecoder.decode(resource, "UTF-8");
        return build(resource, generic);
    }


    /**
     * resource配置 创建 接口类 instanceof AbstractAPI
     *
     * 携带 Class generic (服务于自定义API返回的数据类型)
     *
     * @param resource
     * @param generic
     * @param <T>
     * @return
     * @throws ConfigurationException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T, K> GenericObjectPool<T> build(String resource, Class<K> generic) throws Exception {
        Configuration configuration = new PropertiesConfiguration(resource);
        return build(configuration, generic);
    }


    /**
     * configuration配置 创建 接口类 instanceof AbstractAPI
     *
     * 携带 Class generic (服务于自定义API返回的数据类型)
     *
     * @param configuration
     * @param generic
     * @param <T>
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public <T, K> GenericObjectPool<T> build(Configuration configuration, Class<K> generic) throws Exception {
        return build(configuration, null, generic);
    }

    /**
     * @param resource
     * @param label
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> GenericObjectPool<T> build(String resource, String label) throws Exception {
        Configuration configuration = new PropertiesConfiguration(resource);
        return this.build(configuration, label);
    }

    /**
     * @param resource
     * @param label
     * @param generic
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T, K> GenericObjectPool<T> build(String resource, String label, Class<K> generic) throws Exception {
        Configuration configuration = new PropertiesConfiguration(resource);
        return this.build(configuration, label, generic);
    }

    /**
     * @param configuration
     * @param label
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> GenericObjectPool<T> build(Configuration configuration, String label) throws Exception {
        return build(configuration, label, Object.class);
    }

    /**
     * @param configuration
     * @param label
     * @param generic
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T, K> GenericObjectPool<T> build(Configuration configuration, String label, Class<K> generic) throws Exception {
        Context context = new Context(configuration);
        context.setLabel(label);

        PooledObjectFactory<T> factory = new AbstractAPIPoolFactory(context);

        int MAXIDLE = context.findNumberAccordingKey(Constant.MAXIDLE, 5).intValue();
        int MINIDLE = context.findNumberAccordingKey(Constant.MINIDLE, 1).intValue();
        int MAXTOTAL = context.findNumberAccordingKey(Constant.MAXTOTAL, 20).intValue();
        long MINEVICTABLEIDLETIMEMILLIS = context.findNumberAccordingKey(Constant.MINEVICTABLEIDLETIMEMILLIS, 1800000).longValue();
        long TIMEBETWEENEVICTIONRUNSMILLIS = context.findNumberAccordingKey(Constant.TIMEBETWEENEVICTIONRUNSMILLIS, 1800000 * 2L).longValue();
        boolean TESTONBORROW = context.findBooleanAccordingKey(Constant.TESTONBORROW, true);
        boolean TESTONRETURN = context.findBooleanAccordingKey(Constant.TESTONRETURN, false);
        boolean TESTWHILEIDLE = context.findBooleanAccordingKey(Constant.TESTWHILEIDLE, false);
        long MAXWAITMILLIS = context.findNumberAccordingKey(Constant.MAXWAITMILLIS, 60000).longValue();
        boolean LIFO = context.findBooleanAccordingKey(Constant.LIFO, true);
        boolean BLOCKWHENEXHAUSTED = context.findBooleanAccordingKey(Constant.BLOCKWHENEXHAUSTED, true);
        int NUMTESTSPEREVICTIONRUN = context.findNumberAccordingKey(Constant.NUMTESTSPEREVICTIONRUN, 3).intValue();

        logger.info("初始化线程池: MAXIDLE={}, MINIDLE={}, MAXTOTAL={}, MINEVICTABLEIDLETIMEMILLIS={}, TIMEBETWEENEVICTIONRUNSMILLIS={}, TESTONBORROW={}, TESTONRETURN={}, TESTWHILEIDLE={}, MAXWAITMILLIS={}, LIFO={}, BLOCKWHENEXHAUSTED={}, NUMTESTSPEREVICTIONRUN={} ", MAXIDLE, MINIDLE, MAXTOTAL, MINEVICTABLEIDLETIMEMILLIS, TIMEBETWEENEVICTIONRUNSMILLIS, TESTONBORROW, TESTONRETURN, TESTWHILEIDLE, MAXWAITMILLIS, LIFO, BLOCKWHENEXHAUSTED, NUMTESTSPEREVICTIONRUN);

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        // 最大空闲数
        poolConfig.setMaxIdle(MAXIDLE);
        // 最小空闲数, 池中只有一个空闲对象的时候，池会在创建一个对象，并借出一个对象，从而保证池中最小空闲数为1
        poolConfig.setMinIdle(MINIDLE);
        // 最大池对象总数
        poolConfig.setMaxTotal(MAXTOTAL);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        poolConfig.setMinEvictableIdleTimeMillis(MINEVICTABLEIDLETIMEMILLIS);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        poolConfig.setTimeBetweenEvictionRunsMillis(TIMEBETWEENEVICTIONRUNSMILLIS);
        // 在获取对象的时候检查有效性, 默认false
        poolConfig.setTestOnBorrow(TESTONBORROW);
        // 在归还对象的时候检查有效性, 默认false
        poolConfig.setTestOnReturn(TESTONRETURN);
        // 在空闲时检查有效性, 默认false
        poolConfig.setTestWhileIdle(TESTWHILEIDLE);
        // 最大等待时间， 默认的值为-1，表示无限等待。
        poolConfig.setMaxWaitMillis(MAXWAITMILLIS);
        // 是否启用后进先出, 默认true
        poolConfig.setLifo(LIFO);
        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        poolConfig.setBlockWhenExhausted(BLOCKWHENEXHAUSTED);
        // 每次逐出检查时 逐出的最大数目 默认3
        poolConfig.setNumTestsPerEvictionRun(NUMTESTSPEREVICTIONRUN);
        // 创建对象池
        GenericObjectPool<T> pool = new GenericObjectPool<T>(factory, poolConfig);

        return pool;
    }

}
