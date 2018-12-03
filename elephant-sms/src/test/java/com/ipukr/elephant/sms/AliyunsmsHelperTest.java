package com.ipukr.elephant.sms;

import com.ipukr.elephant.sms.config.AliyunSmsConfig;
import com.ipukr.elephant.sms.third.AliyunSms;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2017/9/6.
 */

public class AliyunsmsHelperTest {

    private AliyunSms sms;

    @Before
    public void setUp() throws Exception {
        AliyunSmsConfig config = new AliyunSmsConfig.Builder()
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


}
