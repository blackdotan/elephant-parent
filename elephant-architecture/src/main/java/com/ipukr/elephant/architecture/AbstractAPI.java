package com.ipukr.elephant.architecture;

import com.ipukr.elephant.architecture.context.Context;

/**
 * 抽象结果类型 / 集成接口后可以使用Context数据
 *
 * Created by wmw on 12/27/16.
 *
 * @param <T> 需要注入的接口泛型对象/可不填
 */
public abstract class AbstractAPI<T> {
    
    protected Context context = null;

    protected Class<T> generic;

    public AbstractAPI(Context context){
        this.context = context;
    }

    public AbstractAPI(Context context, Class<T> generic){
        this.context = context;
        this.generic = generic;
    }
}
