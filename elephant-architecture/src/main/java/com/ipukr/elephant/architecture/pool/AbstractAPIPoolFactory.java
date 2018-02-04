package com.ipukr.elephant.architecture.pool;

import com.ipukr.elephant.architecture.AbstractAPI;
import com.ipukr.elephant.architecture.constant.Constant;
import com.ipukr.elephant.architecture.context.Context;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.lang.reflect.Constructor;

/**
 * Created by ryan on 上午2:21.
 */
public class AbstractAPIPoolFactory<T extends AbstractAPI> extends BasePooledObjectFactory<T> {

    private Context context =  null;

    public AbstractAPIPoolFactory(Context context) {
        this.context = context;
    }

    @Override
    public T create() throws Exception {
        String clazz = context.findStringAccordingKey(Constant.CLASS);
        Constructor constructor = Class.forName(clazz).getConstructor(Context.class);
        return (T) constructor.newInstance(context);
    }

    @Override
    public PooledObject<T> wrap(T obj) {
        return new DefaultPooledObject<T>(obj);
    }
}
