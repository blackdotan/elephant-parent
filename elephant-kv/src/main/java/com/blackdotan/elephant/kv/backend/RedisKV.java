package com.blackdotan.elephant.kv.backend;

import com.blackdotan.elephant.kv.KV;
import com.blackdotan.elephant.kv.config.IpukrRedisConfig;
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
//@Component
public class RedisKV implements KV {

    private static final Logger logger = LoggerFactory.getLogger(RedisKV.class);

    private IpukrRedisConfig iconfig;

    private JedisPool pool;

    public RedisKV(IpukrRedisConfig iconfig) {
        this.iconfig = iconfig;
        this.init();
    }

    private void init() {
        logger.debug("初始化组件 {}, config={}", RedisKV.class.getCanonicalName(), iconfig.toString());
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(iconfig.getMaxTotal());
        config.setMaxIdle(iconfig.getMaxIdle());
        config.setMaxWaitMillis(iconfig.getTimeout());
        config.setTestOnBorrow(iconfig.getTestOnBorrow());
        if(iconfig.getAuth()==null || iconfig.getAuth().equals("")){
            pool = new JedisPool(config, iconfig.getAddress(), iconfig.getPort(), iconfig.getTimeout());
        }else{
            pool = new JedisPool(config, iconfig.getAddress(), iconfig.getPort(), iconfig.getTimeout(), iconfig.getAuth(), iconfig.getDb());
        }
    }





    @Override
    public void add(String key, byte[] value) {
        Jedis jedis = pool.getResource();
        jedis.set(key.getBytes(), value);
        jedis.close();
    }

    @Override
    public void add(String key, byte[] value, Long ttl) {
        Jedis jedis = pool.getResource();
        jedis.set(key.getBytes(), value);
        jedis.expire(key, ttl.intValue());
        jedis.close();
    }

    @Override
    public byte[] get(String key) throws IOException {
        Jedis jedis = pool.getResource();
        byte[] t = jedis.get(key.getBytes());
        jedis.close();
        return t;
    }

    @Override
    public byte[] get(String key, byte[] def) throws IOException {
        Jedis jedis = pool.getResource();
        byte[] t = null;
        if(jedis.exists(key)){
            t = jedis.get(key.getBytes());
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
    public List<byte[]> match(String pattern) throws IOException {
        List<byte[]> arr = new ArrayList<byte[]>();
        Jedis jedis = pool.getResource();

        for(String text : jedis.keys(pattern)) {
            arr.add(jedis.get(text.getBytes()));
        }
        jedis.close();
        return arr;
    }

    @Override
    public void put(String key, byte[] value) {
        Jedis jedis = pool.getResource();
        jedis.lpush(key.getBytes(), value);
        jedis.close();
    }

    @Override
    public void put(String key, byte[] value, Long ttl) {
        Jedis jedis = pool.getResource();
        jedis.lpush(key.getBytes(), value);
        jedis.expire(key, ttl.intValue());
        jedis.close();
    }

    @Override
    public byte[] pop(String key) throws IOException {
        Jedis jedis = pool.getResource();
        byte[] result = null;
        if(jedis.exists(key)) {
             result = jedis.lpop(key.getBytes());

        }
        jedis.close();
        return result;
    }

    @Override
    public byte[] pop(String key, byte[] def) throws IOException {
        Jedis jedis = pool.getResource();
        byte[] t = def;
        if(jedis.exists(key)) {
            t = jedis.lpop(key.getBytes());
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

}
