package com.blackdotan.elephant.common.template;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;

/**
 * @param <T> Entity对象(eg: User, Book)
 * @param <K> 主键数据类型(eg: Integer, String)
 *
 * @author ryan
 *
 * Created by ryan on 上午1:53.
 */
public abstract class AbstractService<T, K extends java.io.Serializable> implements InterfaceService<T, K> {

    /**
     * 判断对象是否存在
     *
     * @param instance Domain对象
     * @return 是否存在
     * @throws Exception 异常
     */
    @Override
    public boolean exist(T instance) throws Exception {
        return getMapper().exist(instance);
    }

    /**
     * 根据主键, 获取数据
     *
     * @param primaryKey 主键
     * @return Domain对象
     * @throws Exception 异常
     */
    @Override
    public T find(K primaryKey) throws Exception {
        return getMapper().selectByPrimaryKey(primaryKey);
    }

    /**
     * 分页, 获取数据
     * @param bounds 分页对象
     * @return Domain对象列表
     * @throws Exception 异常
     */
    @Override
    public List<T> find(PageBounds bounds) throws Exception {
        return getMapper().findAll(bounds);
    }

    /**
     * 获取所有数据
     * @return Domain对象 列表
     * @throws Exception 异常
     */
    @Override
    public List<T> findAll() throws Exception {
        return getMapper().findAll();
    }

    /**
     * 新增数据
     * @param instance Domain对象
     * @return 是否插入成功
     * @throws Exception 异常
     */
    @Override
    public boolean insert(T instance) throws Exception {
        return getMapper().insertSelective(instance)>0;
    }

    /**
     * 根据主键, 修改数据
     * @param instance Domain对象
     * @return 是否更新成功
     * @throws Exception 异常
     */
    @Override
    public boolean update(T instance) throws Exception {
        return getMapper().updateByPrimaryKeySelective(instance) > 0;
    }

    /**
     * 匹配查询
     * @param instance Domain对象
     * @return Domain对象匹配列表
     * @throws Exception 异常
     */
    @Override
    public List<T> query(T instance) throws Exception {
        return getMapper().query(instance);
    }

    /**
     * 分页, 匹配查询
     * @param instance Domain对象
     * @param bounds 分页参数
     * @return Domain对象匹配列表
     * @throws Exception 异常
     */
    @Override
    public List<T> query(T instance, PageBounds bounds) throws Exception {
        return getMapper().query(instance, bounds);
    }

    /**
     * 模糊查询
     * @param instance Domain对象
     * @return Domain对象搜索匹配列表
     * @throws Exception 异常
     */
    @Override
    public List<T> search(T instance) throws Exception  {
        return getMapper().search(instance);
    }

    /**
     * 分页, 模糊查询
     * @param instance Domain对象
     * @param bounds 分页对象
     * @return Domain对象搜索匹配列表
     * @throws Exception 异常
     */
    @Override
    public List<T> search(T instance, PageBounds bounds) throws Exception {
        return getMapper().search(instance, bounds);
    }

    /**
     * 根据主键, 删除数据
     * @param primaryKey 主键
     * @return 是否删除成功
     * @throws Exception 异常
     */
    @Override
    public boolean delete(K primaryKey) throws Exception {
        return getMapper().deleteByPrimaryKey(primaryKey)>0;
    }

    /**
     * 数据抽样
     * @return 随机Domain对象
     */
    @Override
    public T example() {
        return getMapper().example();
    }

    /**
     * @param n 随机数目
     * @return Domain对象列表
     */
    @Override
    public List<T> nexample(int n) {
        return getMapper().nexample(n);
    }

    /**
     * 获取实体Mapper对象 <br>
     *
     * 此方法需要重写, 返回Mybatis Mapper接口绑定类型(eg:返回@Resource: UserMapper iUserMapper)
     *
     * @return Mybatis Mapper 接口对象
     */
    public abstract AbstractMapper<T, K> getMapper();

}
