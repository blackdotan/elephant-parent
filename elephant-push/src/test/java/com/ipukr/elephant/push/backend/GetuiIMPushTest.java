package com.ipukr.elephant.push.backend;

import com.ipukr.elephant.push.IPush;
import com.ipukr.elephant.push.config.GetuiIMPushConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ryan on 下午5:43.
 */
public class GetuiIMPushTest{

    private IPush mIPush;

    /**
     * 个推配置
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        GetuiIMPushConfig config = GetuiIMPushConfig.custom()
                .appid("WwZVpeNxbG7yZTdRV9Mhf6")
                .appkey("uotLQQq7Mm9gYHkQpzx4l7")
                .master("4tJ62jk6ug88C8kGkFkJP4")
                .offline(true)
                .build();
        mIPush = new GetuiIMPush(config);
    }

    /**
     * 推送到单个设备
     */
    @Test
    public void testSingle() {
        mIPush.push("7db50e64153e078a2e8bc398e43bd3c1", "应用推送","测试推送1003");
    }

    /**
     * 推送到多个设备
     */
    @Test
    public void testList() {
        mIPush.push(Arrays.asList(
                "8f2596f92137af2d8d4904b72e4c77bb",
                "7db50e64153e078a2e8bc398e43bd3c1",
                "637559faca63ef0f4241b6c3a80208f4"),
                "应用推送",
                "测试推送1004");
    }
}
