package com.ipukr.elephant.kv;

import com.ipukr.elephant.kv.backend.RedisKV;
import com.ipukr.elephant.kv.config.IpukrRedisConfig;
import com.ipukr.elephant.kv.entity.Client;
import com.ipukr.elephant.utils.JsonUtils;
import com.ipukr.elephant.utils.SerializableUtils;
import com.ipukr.elephant.utils.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ryan on 下午6:02.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedisKV.class, IpukrRedisConfig.class})
@EnableConfigurationProperties
public class KVTest {

    @Autowired
    private KV mKV;


    @Test
    public void add() throws Exception {
        for (int i = 0; i < 100; i++) {
            Client client = new Client();
            client.setId(i);
            mKV.add(StringUtils.uuid(), SerializableUtils.serialize(client));
        }
    }

    @Test
    public void name() throws IOException {
        byte[] bytes = mKV.get("dcaad4a9-6955-46e7-ba51-23fad2cdd841");
        Client client = (Client) SerializableUtils.deserialize(bytes);
        System.out.println(JsonUtils.parserObj2String(client));
    }

    @Test
    public void keys() throws IOException {
        List<byte[]> values = mKV.match("*");



    }
}
