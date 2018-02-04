package com.ipukr.elephant.kv;

import com.ipukr.elephant.architecture.factory.Factory;
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
        final KV<Client> mKV = Factory.getInstance().build("config.properties", "redis", Client.class);
        CountDownLatch latch = new CountDownLatch(num);
        for(int i=0; i<num; i++) {
            final int thd = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int j = 0;
                    while (j > 10000) {
                        String key = StringUtils.uuid();
                        Client client = new Client(1, "ryan");
                        String msg = StringUtils.easyAppend("Thread:{}, Add {}", thd, JsonUtils.parserObj2String(client));
                        System.out.println(msg);
                        mKV.add(key, client, 100L);
                        j ++;
                    }
                }
            }).start();
        }

        try {
            // 主线程等待
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
