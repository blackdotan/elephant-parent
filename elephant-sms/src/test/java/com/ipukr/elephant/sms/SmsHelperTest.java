package com.ipukr.elephant.sms;

import com.ipukr.elephant.sms.config.AliyunSmsConfig;
import com.ipukr.elephant.sms.third.AliyunSms;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by wmw on 12/13/16.
 */
public class SmsHelperTest {

    private Sms mSms;

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
        mSms = new AliyunSms(config);
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
