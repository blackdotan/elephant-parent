package com.ipukr.elephant.sms;

import com.ipukr.elephant.sms.config.AliyunSmsConfig;
import com.ipukr.elephant.sms.third.AliyunSms;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2017/9/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AliyunSmsConfig.class, AliyunSms.class})
@EnableConfigurationProperties
public class AliyunsmsHelperTest {

    @Autowired
    private AliyunSms sms;

    @Test
    public void send1() throws Exception {
        SmsResponse response = sms.send("15805903814", "6666");
        System.out.println(response.getMsg());
        Assert.assertEquals(SmsResponse.Status.Success, response.getStatus());
    }


}
