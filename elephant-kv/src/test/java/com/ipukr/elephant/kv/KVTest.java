package com.ipukr.elephant.kv;

import com.ipukr.elephant.architecture.factory.Factory;
import com.ipukr.elephant.kv.backend.RedisKV;
import com.ipukr.elephant.kv.entity.Client;
import com.ipukr.elephant.utils.JsonUtils;
import com.ipukr.elephant.utils.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ryan on 下午6:02.
 */
public class KVTest {

    private KV<Client> mKV;

    @Before
    public void init() throws Exception {

    }

    @Test
    public void add() throws Exception {
        final int num = 20;
        mKV = Factory.getInstance().build("config.properties", "redis", Client.class);
        int j = 0;
        while (j < 10000) {
            String key = StringUtils.uuid();
            Client client = new Client(1, "ryan");
            String msg = StringUtils.easyAppend("Thread:{}, Add {}", 1, JsonUtils.parserObj2String(client));
            System.out.println(msg);
            mKV.add(key, client, 100L);
            j ++;
        }
    }

    @Test
    public void add2() {
        mKV = RedisKV.custom()
                .addresses("10.106.130.182")
                .port(6379)
                .db(1)
                .password("admin123@$^!")
                .maxTotal(20)
                .maxIdle(10)
                .maxActive(10)
                .maxWait(10)
                .testOnBorrow(true)
                .timeout(7000)
                .generic(Client.class)
                .build();
        int j = 0;
        while (j < 10000) {
            String key = StringUtils.uuid();
            Client client = new Client(1, "ryan");
            String msg = StringUtils.easyAppend("Thread:{}, Add {}", 1, JsonUtils.parserObj2String(client));
            System.out.println(msg);
            mKV.add(key, client, 100L);
            j ++;
        }

    }
}
