package com.blackdotan.elephant.kv;

import com.blackdotan.elephant.kv.backend.RedisKV;
import com.blackdotan.elephant.kv.config.IpukrRedisConfig;
import com.blackdotan.elephant.kv.entity.Client;
import com.blackdotan.elephant.utils.JsonUtils;
import com.blackdotan.elephant.utils.SerializableUtils;
import com.blackdotan.elephant.utils.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by ryan on 下午6:02.
 */
public class KVTest {

    private KV mKV;

    /**
     * 初始化Redis配置
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        IpukrRedisConfig iconfig = IpukrRedisConfig.custom()
                .address("10.244.69.170")
                .port(6379)
                .auth("admin123@$^!")
                .db(11)
                .build();
        mKV = new RedisKV(iconfig);
    }

    /**
     * 测试新增记录
     * @throws Exception
     */
    @Test
    public void add() throws Exception {
        for (int i = 0; i < 100; i++) {
            Client client = new Client();
            client.setId(i);
            mKV.add(StringUtils.uuid(), SerializableUtils.serialize(client));
        }
    }

    /**
     * 测试获取数据
     * @throws IOException
     */
    @Test
    public void get() throws IOException {
        byte[] bytes = mKV.get("dcaad4a9-6955-46e7-ba51-23fad2cdd841");
        Client client = (Client) SerializableUtils.deserialize(bytes);
        System.out.println(JsonUtils.parserObj2String(client));
    }

    /**
     * 测试获取匹配数据
     * @throws IOException
     */
    @Test
    public void keys() throws IOException {
        List<byte[]> values = mKV.match("*");
    }
}
