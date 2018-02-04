package com.ipukr.elephant.architecture.third;

import com.ipukr.elephant.architecture.AbstractAPI;
import com.ipukr.elephant.architecture.KV;
import com.ipukr.elephant.architecture.context.Context;
import com.ipukr.elephant.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于内存形式的KV <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/19.
 */
public class MemoryKV extends AbstractAPI implements KV {

    private static final Logger logger = LoggerFactory.getLogger(MemoryKV.class);

    private static final String MAP_CLASS = "map.class";
    private static final String EXPIRE = "expire";

    private Integer expire = 999_999_999;

    private Map<String, Object> map = null;

    /**
     * 构造方法重载
     *
     * <p>
     *  AbstractAPI实现类必须继承:
     *  com.ipukr.elephant.architecture.context.Context
     *  或
     *  com.ipukr.elephant.architecture.context.Context, Class 构造方法
     * </p>
     *
     * @param context
     */
    public MemoryKV(Context context) throws Exception {
        super(context);
        this.init();
    }

    private void init() throws Exception {
        // 获取expire
        expire = context.findNumberAccordingKey(EXPIRE, expire).intValue();
        // 根据MAP_CLASS, 实例化map
        String mapClazz = context.findStringAccordingKey(MAP_CLASS, "java.util.HashMap");
        map = (Map) Class.forName(mapClazz).newInstance();
        logger.info("init MemoryKV, expire={}, map.calss={}", expire, map.getClass().getName());
    }

    /**
     * 新增Key-Value
     * @param key
     * @param value
     */
    @Override
    public void add(String key, Object value) {
        logger.info("add key/value: key={}, value={}", key, value.toString());
        map.put(key, value);
    }

    /**
     * 删除Key
     * @param key
     */
    @Override
    public void remove(String key) {
        logger.info("remove key: key={}", key);
        if(map.containsKey(key))
            map.remove(key);
    }

    /**
     * 判断Key是否存在
     * @param key
     */
    @Override
    public boolean exists(String key) {
        logger.info("exists key: key={}", key);
        return map.containsKey(key);
    }

    @Override
    public Map<String, Object> match(String pattern) {
        try {
            Map ret = (Map) Class.forName(context.findStringAccordingKey(MAP_CLASS)).newInstance();
            for(Map.Entry<String,Object> entry : map.entrySet()) {
                if (entry.getKey().matches(pattern)) {
                    ret.put(entry.getKey(), entry.getValue());
                }
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(ExceptionUtils.castEx2Str(e));
        }
        return null;
    }
}
