package com.ipukr.elephant.push.backend;

import com.ipukr.elephant.push.IPush;
import com.ipukr.elephant.push.config.GetuiIMPushConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ryan on 下午5:43.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GetuiIMPush.class, GetuiIMPushConfig.class})
@EnableConfigurationProperties
public class GetuiIMPushTest{

    @Autowired
    private IPush mIPush;


    @Test
    public void testSingle() {
        mIPush.push("7db50e64153e078a2e8bc398e43bd3c1", "应用推送","测试推送1003");
    }

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
