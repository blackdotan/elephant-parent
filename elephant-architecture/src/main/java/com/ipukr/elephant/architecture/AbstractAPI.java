package com.ipukr.elephant.architecture;

import com.ipukr.elephant.architecture.context.Context;

/**
 * Created by wmw on 12/27/16.
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
