package com.ipukr.elephant.architecture.factory;

import com.ipukr.elephant.architecture.configuration.Configuration;
import com.ipukr.elephant.architecture.configuration.PropertiesConfiguration;
import com.ipukr.elephant.architecture.constant.Constant;
import com.ipukr.elephant.architecture.context.Context;
import org.apache.commons.configuration.ConfigurationException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;

/**
 * Created by wmw on 12/27/16.
 */
public class Factory {

    private static com.ipukr.elephant.architecture.factory.Factory factory;

    private Factory() {}

    /**
     * API 单例, 创建工厂
     *
     * @return
     */
    public synchronized static com.ipukr.elephant.architecture.factory.Factory getInstance() {
        if(factory == null){
            factory = new com.ipukr.elephant.architecture.factory.Factory();
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
    public <T> T build() throws Exception {
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
    public <T> T  build(String resource) throws Exception {
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
    public <T> T build(Configuration configuration) throws Exception {
        Context context = new Context(configuration);
        String clazz = context.findStringAccordingKey(Constant.CLASS);
        Constructor constructor = Class.forName(clazz).getConstructor(Context.class);
        return (T) constructor.newInstance(context);
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
    public <T, K> T build(Class<K> generic) throws Exception {
        String resource = com.ipukr.elephant.architecture.factory.Factory.class.getResource("/").getPath().concat(Constant.RESOURCE);
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
    public <T, K> T  build(String resource, Class<K> generic) throws Exception {
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
    public <T, K> T build(Configuration configuration, Class<K> generic) throws Exception {
        Context context = new Context(configuration);
        String clazz = context.findStringAccordingKey(Constant.CLASS);
        Constructor constructor = Class.forName(clazz).getConstructor(Context.class, Class.class);
        return (T) constructor.newInstance(context, generic);
    }

    /**
     * @param resource
     * @param label
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T build(String resource, String label) throws Exception {
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
    public <T, K> T build(String resource, String label, Class<K> generic) throws Exception {
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
    public <T> T build(Configuration configuration, String label ) throws Exception {
        Context context = new Context(configuration);
        context.setLabel(label);
        String clazz = context.findStringAccordingKey(Constant.CLASS);
        Constructor constructor = Class.forName(clazz).getConstructor(Context.class);
        return (T) constructor.newInstance(context);
    }

    /**
     * @param configuration
     * @param label
     * @param generic
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T, K> T build(Configuration configuration, String label, Class<K> generic) throws Exception {
        Context context = new Context(configuration);
        context.setLabel(label);
        String clazz = context.findStringAccordingKey(Constant.CLASS);
        Constructor constructor = Class.forName(clazz).getConstructor(Context.class, Class.class);
        return (T) constructor.newInstance(context, generic);
    }

}
