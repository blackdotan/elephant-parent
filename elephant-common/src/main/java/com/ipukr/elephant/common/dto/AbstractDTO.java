package com.ipukr.elephant.common.dto;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.ipukr.elephant.utils.DataUtils;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 请使用 Live Template 手动输入生成模板方式
 * Created by ryan on 下午2:03.
 */
@Deprecated
public abstract class AbstractDTO {

    /**
     * 数据拷贝
     * @param k
     * @param clazz
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> T parser(K k, Class<T> clazz) {
        T t = DataUtils.copyProperties(k, clazz);
        return t;
    }

    /**
     * 数据拷贝
     * @param ks
     * @param clazz
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> List<T> parser(List<K> ks, Class<T> clazz) {
        // 处理空
        if (ks == null || ks.size() == 0) {
            return new ArrayList<T>();
        } else {
            return ks.stream().map(e->parser(e, clazz)).collect(Collectors.toList());
        }
    }

    /**
     * 数据拷贝/ 处理分页问题
     * @param k
     * @param clazz
     * @param <K>
     * @return
     */
    public static <T, K> T parserWithPaginator(K k, Class<T> clazz) {
        T t = DataUtils.copyProperties(k, clazz);
        return t;
    }

    /**
     * 数据拷贝/ 处理分页问题
     * @param ks
     * @param clazz
     * @param <K>
     * @return
     */
    public static <T, K> List<T> parserWithPaginator(List<K> ks, Class<T> clazz) {
        List<T> arrays = new ArrayList<T>();
        if(ks instanceof PageList) {
            arrays = new PageList<T>(((PageList) ks).getPaginator());
        }
        for(K k : ks) {
            arrays.add(parserWithPaginator(k, clazz));
        }
        return arrays;
    }
}
