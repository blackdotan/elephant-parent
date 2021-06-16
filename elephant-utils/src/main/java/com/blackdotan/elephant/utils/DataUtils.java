package com.blackdotan.elephant.utils;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by wmw on 16/11/17.
 */
public class DataUtils {

    /**
     * 拷贝K类型 到 新T乐行
     *
     * @param k 泛型K实例
     * @param clazz 目标类型K.class
     * @param <T> 泛型T
     * @param <K> 泛型K
     * @return 目标类型T实例
     * */
    public static <T, K> T copyProperties(K k, Class<T> clazz){
        try {
            T instance = clazz.newInstance();
            BeanUtils.copyProperties(k, instance);
            return instance;
        }catch (Exception e){
            return null;
        }
    }
    /**
     *
     * 拷贝List 到 新List
     *
     * @param ks 泛型K实例列表
     * @param clazz 目标类型K.class
     * @return 目标类型T实例列表
     * @param <T> 泛型T
     * @param <K> 泛型K
     * @throws Exception 反射实例异常
     * */
    public static <T,K> List<T> copyProperties(List<K> ks, Class<T> clazz) throws Exception {
        List<T> ts = ks instanceof PageList ?new PageList<T>( ((PageList)ks).getPaginator() ): new ArrayList<T>();
        for(K k : ks){
            T instance = clazz.newInstance();
            BeanUtils.copyProperties(k, instance);
            ts.add(instance);
        }
        return ts;
    }

    /**
     * 拷贝K 到 新T 忽略空
     *
     * @param k 泛型K实例
     * @param clazz 目标类型K.class
     * @param <T> 泛型T
     * @param <K> 泛型K
     * @return 目标泛型T实例
     */
    public static <T,K> T copyPropertiesIgnoreNull(K k, Class<T> clazz){
        try {
            T instance = clazz.newInstance();
            BeanUtils.copyProperties(k, instance, getNullPropertyNames(k));
            return instance;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 拷贝List 到 新List 忽略空
     *
     * @param ks 泛型K实例列表
     * @param clazz 目标类型K.class
     * @param <T> 泛型T
     * @param <K> 泛型K
     * @return 目标泛型T实例列表
     */
    public static <T,K> List<T> copyPropertiesIgnoreNull(List<K> ks, Class<T> clazz) {
        List<T> ts = ks instanceof PageList ?new PageList<T>( ((PageList)ks).getPaginator() ): new ArrayList<T>(ks.size());
        for(K k :ks){
            ts.add(copyPropertiesIgnoreNull(k, clazz));
        }
        return ts;
    }

    /**
     * 拷贝K 到 T
     *
     * @param k 泛型K实例
     * @param t 泛型T实例
     * @param <T> 泛型K
     * @param <K> 泛型T
     */
    public static <T,K> void copyPropertiesIgnoreNull(K k, T t){
        BeanUtils.copyProperties(k, t, getNullPropertyNames(k));
    }

    /**
     * 获取所有 null 属性名
     * @param source 处理对象
     * @return 空属性名数组
     */
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


    /**
     * 拷贝map 到 新T
     * @param map 待拷贝Map
     * @param clazz T.class
     * @param <T> 泛型T
     * @return T实例
     * @throws IllegalAccessException T实例化异常
     * @throws InstantiationException T实例化异常
     * @throws IntrospectionException T实例化异常
     * @throws InvocationTargetException T实例化异常
     */
    public static <T> T copyProperties(Map<String,Object> map, Class<T> clazz) throws IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException {
        T instance = clazz.newInstance();
        BeanInfo info = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] props = info.getPropertyDescriptors();
        for(PropertyDescriptor prop : props){
            String key = prop.getName();
            if(map.containsKey(key)){
                Method method = prop.getWriteMethod();
                Class<?>[] classes = method.getParameterTypes();
                if(map.get(key)!=null && classes.length==1 && map.get(key).getClass()==classes[0]) {
                    method.invoke(instance, map.get(key));
                }
            }
        }
        return instance;
    }

}
