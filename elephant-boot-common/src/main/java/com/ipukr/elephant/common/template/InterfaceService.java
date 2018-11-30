package com.ipukr.elephant.common.template;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;

/**
 * Created by ryan on 上午1:49.
 * @param <T> Entity对象(eg: User, Book)
 * @param <K> 主键数据类型(eg: Integer, String)
 */
public interface InterfaceService <T, K extends java.io.Serializable> {

    /**
     * 是否存在
     * @param instance
     * @return
     * @throws Exception
     */
    boolean exist(T instance) throws Exception;
    /**
     * 新增
     * @param instance
     * @return
     * @throws Exception
     */
    boolean insert(T instance) throws Exception;

    /**
     * 修改
     * @param instance
     * @return
     * @throws Exception
     */
    boolean update(T instance) throws Exception;

    /**
     * 根据主键获取数据
     * @param primaryKey
     * @return
     * @throws Exception
     */
    T find(K primaryKey) throws Exception;

    /**
     * 分页获取数据
     * @param bounds
     * @return
     * @throws Exception
     */
    List<T> find(PageBounds bounds) throws Exception;

    /**
     * 获取所有
     * @return
     * @throws Exception
     */
    List<T> findAll() throws Exception;

    /**
     * 匹配查询
     * @param instance
     * @return
     * @throws Exception
     */
    List<T> query(T instance) throws Exception;

    /**
     * 分页匹配查询
     * @param instance
     * @param bounds
     * @return
     * @throws Exception
     */
    List<T> query(T instance, PageBounds bounds) throws Exception;

    /**
     * 模糊查询
     * @param instance
     * @return
     * @throws Exception
     */
    List<T> search(T instance) throws Exception;

    /**
     * 分页模糊查询
     * @param instance
     * @param bounds
     * @return
     * @throws Exception
     */
    List<T> search(T instance, PageBounds bounds) throws Exception;

    /**
     * 根据主键删除数据
     * @param primaryKey
     * @return
     * @throws Exception
     */
    boolean delete(K primaryKey) throws Exception;

    /**
     * 数据抽样接口
     *
     * BUG:同一个事务下example返回结果都相同
     *
     * @return
     */
    T example();

    /**
     * 数据接口抽样
     *
     * @param n
     * @return
     */
    List<T> nexample(int n);
}
