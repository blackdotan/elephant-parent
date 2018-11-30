package com.ipukr.elephant.sms;

import com.ipukr.elephant.sms.config.AliyunSmsConfig;
import com.ipukr.elephant.sms.third.AliyunSms;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wmw on 12/13/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AliyunSmsConfig.class, AliyunSms.class})
@EnableConfigurationProperties
public class SmsHelperTest {

    @Autowired
    private Sms mSms;

    @Before
    public void init() throws Exception {
    }


    @Test
    public void send1() throws Exception {
        Assert.assertEquals(SmsResponse.Status.Success, mSms.send("15805903814", "6666").getStatus());
    }

    @Test
    public void send2() throws Exception {
        boolean bool = mSms.send("15805903814", "8888").getStatus() == SmsResponse.Status.Success;
        Thread.sleep(60000);
        bool = bool && mSms.send("15805903814", "7777").getStatus() == SmsResponse.Status.Success;
        Assert.assertEquals(true, bool);
    }
}
