package com.ipukr.elephant.kv.backend;

import com.ipukr.elephant.architecture.AbstractAPI;
import com.ipukr.elephant.architecture.context.Context;
import com.ipukr.elephant.kv.KV;
import com.ipukr.elephant.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by ryan on 下午5:47.
 */
public class RedisKV<T> extends AbstractAPI<T> implements KV<T> {

    private static final Logger logger = LoggerFactory.getLogger(RedisKV.class);

    // 资源池配置
    public static final String REDIS_MAXTOTAL = "max_total";
    public static final String REDIS_MAXIDLE = "max_idle";
    public static final String REDIS_MAXWAIT = "max_wait";
    public static final String REDIS_MAXACTIVE = "max_active";
    // 连接配置
    public static final String REDIS_TEST_ON_BORROW = "test_on_borrow";
    public static final String REDIS_TIMEOUT = "timeout";
    public static final String REDIS_ADDRESS = "address";
    public static final String REDIS_PORT = "port";
    public static final String REDIS_AUTH = "auth";
    public static final String REDIS_DB = "db";


    private String addresses ;
    private Integer port ;
    private String password ;
    private Integer db ;
    private Integer maxTotal ;
    private Integer maxIdle ;
    private Integer maxWait ;
    private Integer maxActive ;
    private Boolean testOnBorrow ;
    private Integer timeout ;

    private JedisPool pool;

    public RedisKV(Context context) {
        super(context);
        addresses = context.findStringAccordingKey(REDIS_ADDRESS);
        port = Integer.valueOf(context.findStringAccordingKey(REDIS_PORT));
        password = context.findStringAccordingKey(REDIS_AUTH);
        db = Integer.valueOf(context.findStringAccordingKey(REDIS_DB));
        maxTotal = Integer.valueOf(context.findStringAccordingKey(REDIS_MAXTOTAL));
        maxIdle = Integer.valueOf(context.findStringAccordingKey(REDIS_MAXIDLE));
        maxWait = Integer.valueOf(context.findStringAccordingKey(REDIS_MAXWAIT));
        maxActive = Integer.valueOf(context.findStringAccordingKey(REDIS_MAXACTIVE));
        testOnBorrow = Boolean.valueOf( context.findStringAccordingKey(REDIS_TEST_ON_BORROW));
        timeout = Integer.valueOf( context.findStringAccordingKey(REDIS_TIMEOUT));
        logger.info("Setting maxTotal={}, maxIdle={}, maxWait={}. testOnBorrow={}", maxTotal, maxIdle, maxWait, testOnBorrow);
        this.doInit();
    }

    public RedisKV(Context context, Class<T> generic) {
        super(context, generic);
        addresses = context.findStringAccordingKey(REDIS_ADDRESS);
        port = Integer.valueOf(context.findStringAccordingKey(REDIS_PORT));
        password = context.findStringAccordingKey(REDIS_AUTH);
        db = Integer.valueOf(context.findStringAccordingKey(REDIS_DB));
        maxTotal = Integer.valueOf(context.findStringAccordingKey(REDIS_MAXTOTAL));
        maxIdle = Integer.valueOf(context.findStringAccordingKey(REDIS_MAXIDLE));
        maxWait = Integer.valueOf(context.findStringAccordingKey(REDIS_MAXWAIT));
        maxActive = Integer.valueOf(context.findStringAccordingKey(REDIS_MAXACTIVE));
        testOnBorrow = Boolean.valueOf( context.findStringAccordingKey(REDIS_TEST_ON_BORROW));
        timeout = Integer.valueOf( context.findStringAccordingKey(REDIS_TIMEOUT));
        logger.info("Setting maxTotal={}, maxIdle={}, maxWait={}. testOnBorrow={}", maxTotal, maxIdle, maxWait, testOnBorrow);
        this.doInit();
    }

    private RedisKV(Builder builder) {
        super(null);
        addresses = builder.addresses;
        port = builder.port;
        password = builder.password;
        db = builder.db;
        maxTotal = builder.maxTotal;
        maxIdle = builder.maxIdle;
        maxWait = builder.maxWait;
        maxActive = builder.maxActive;
        testOnBorrow = builder.testOnBorrow;
        timeout = builder.timeout;
        pool = builder.pool;
    }


    public void doInit() {


        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWait);
        config.setTestOnBorrow(testOnBorrow);


        logger.info("connect redis: addresses={}, port={}, timeout={}, auth={}, db={}", addresses, port, timeout, password, db);
        if(password==null || password.equals("")){
            pool = new JedisPool(config, addresses, port, timeout);
        }else{
            pool = new JedisPool(config, addresses, port, timeout, password, db);
        }
    }


    @Override
    public void add(String key, T value) {
        Jedis jedis = pool.getResource();
        jedis.set(key, JsonUtils.parserObj2String(value));
        jedis.close();
    }

    @Override
    public void add(String key, T value, Long ttl) {
        Jedis jedis = pool.getResource();
        jedis.set(key, JsonUtils.parserObj2String(value));
        jedis.expire(key, ttl.intValue());
        jedis.close();
    }

    @Override
    public T get(String key) throws IOException {
        Jedis jedis = pool.getResource();
        T t = JsonUtils.parserString2Obj(jedis.get(key), generic);
        jedis.close();
        return t;
    }

    @Override
    public T get(String key, T def) throws IOException {
        Jedis jedis = pool.getResource();
        T t = null;
        if(jedis.exists(key)){
            t = JsonUtils.parserString2Obj(jedis.get(key), generic);
        } else {
            t = def;
        }
        jedis.close();
        return t;
    }

    @Override
    public void del(String key) {
        Jedis jedis = pool.getResource();
        jedis.del(key);
        jedis.close();
    }

    @Override
    public boolean exist(String key) {
        Jedis jedis = pool.getResource();
        boolean bool = jedis.exists(key);
        jedis.close();
        return bool;
    }

    @Override
    public boolean contain(String key) {
        Jedis jedis = pool.getResource();
        boolean bool = jedis.exists(key);
        jedis.close();
        return bool;
    }

    @Override
    public List<T> all() throws Exception {
        List<T> arr = new ArrayList<T>();
        Jedis jedis = pool.getResource();
        for(String text : jedis.keys("*")) {
            arr.add(JsonUtils.parserString2Obj(jedis.get(text), generic));
        }
        jedis.close();
        return arr;
    }

    @Override
    public List<T> match(String pattern) throws IOException {
        List<T> arr = new ArrayList<T>();
        Jedis jedis = pool.getResource();
        for(String text : jedis.keys(pattern)) {
            arr.add(JsonUtils.parserString2Obj(jedis.get(text), generic));
        }
        jedis.close();
        return arr;
    }

    @Override
    public void put(String key, T value) {
        Jedis jedis = pool.getResource();
        jedis.lpush(key, JsonUtils.parserObj2String(value));
        jedis.close();
    }

    @Override
    public void put(String key, T value, Long ttl) {
        Jedis jedis = pool.getResource();
        jedis.lpush(key, JsonUtils.parserObj2String(value));
        jedis.expire(key, ttl.intValue());
        jedis.close();
    }

    @Override
    public T pop(String key) throws IOException {
        Jedis jedis = pool.getResource();
        T t = null;
        if(jedis.exists(key)) {
            String text = jedis.lpop(key);
            t = JsonUtils.parserString2Obj(text, generic);
        }
        jedis.close();
        return t;
    }

    @Override
    public T pop(String key, T def) throws IOException {
        Jedis jedis = pool.getResource();
        T t = def;
        if(jedis.exists(key)) {
            t = JsonUtils.parserString2Obj(jedis.lpop(key), generic);
        }
        jedis.close();
        return t;
    }

    @Override
    public Set<String> keys() {
        Jedis jedis = pool.getResource();
        Set<String> keys = jedis.keys("*");
        jedis.close();
        return keys;
    }

    public final static Builder custom() {
        return new Builder();
    }

    public static final class Builder {
        private String addresses;
        private Integer port;
        private String password;
        private Integer db;
        private Integer maxTotal;
        private Integer maxIdle;
        private Integer maxWait;
        private Integer maxActive;
        private Boolean testOnBorrow;
        private Integer timeout;
        private JedisPool pool;

        public Builder() {
        }

        public Builder addresses(String val) {
            addresses = val;
            return this;
        }

        public Builder port(Integer val) {
            port = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder db(Integer val) {
            db = val;
            return this;
        }

        public Builder maxTotal(Integer val) {
            maxTotal = val;
            return this;
        }

        public Builder maxIdle(Integer val) {
            maxIdle = val;
            return this;
        }

        public Builder maxWait(Integer val) {
            maxWait = val;
            return this;
        }

        public Builder maxActive(Integer val) {
            maxActive = val;
            return this;
        }

        public Builder testOnBorrow(Boolean val) {
            testOnBorrow = val;
            return this;
        }

        public Builder timeout(Integer val) {
            timeout = val;
            return this;
        }

        public Builder pool(JedisPool val) {
            pool = val;
            return this;
        }

        public RedisKV build() {
            return new RedisKV(this);
        }
    }
}
