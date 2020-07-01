package com.ipukr.elephant.sms;

import com.ipukr.elephant.sms.config.AliyunSmsConfig;
import com.ipukr.elephant.sms.third.AliyunSms;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/6.
 */

public class AliyunsmsHelperTest {

    private AliyunSms sms;

    @Before
    public void setUp() throws Exception {
        AliyunSmsConfig config = AliyunSmsConfig.builder()
                .accessKeyId("LTAIe2O2YMLHgt2y")
                .accessKeySecret("Gh0xyxDxwmGzPdXhNGqU6DxeMAl7BJ")
                .domain("dysmsapi.aliyuncs.com")
                .product("Dysmsapi")
                .templateId("SMS_123738450")
                .sign("云扬健身")
                .build();
        sms = new AliyunSms(config);
    }

    @Test
    public void send1() throws Exception {
        SmsResponse response = sms.send("15805903814", "6666");
        System.out.println(response.getMsg());
        Assert.assertEquals(SmsResponse.Status.Success, response.getStatus());
    }

    @Test
    public void testBat() {
        Map m1 = new HashMap<String, String>(){{
            put("code", "6666");
        }};
        Map m2 = new HashMap<String, String>(){{
            put("code", "7777");
        }};
        SmsResponse response = sms.batsend(
                "SMS_123738450",
                Arrays.asList("15805903814","13906041328"),
                Arrays.asList("云扬健身","云扬健身"),
                Arrays.asList(m1, m2));
        System.out.println(response.getMsg());
        Assert.assertEquals(SmsResponse.Status.Success, response.getStatus());
    }
}
