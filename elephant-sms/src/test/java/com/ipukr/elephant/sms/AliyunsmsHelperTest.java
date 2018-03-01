package com.ipukr.elephant.sms;

import com.ipukr.elephant.architecture.factory.Factory;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2017/9/6.
 */
public class AliyunsmsHelperTest {

    private Sms mSms;
    @Before
    public void init() throws Exception {
        mSms = Factory.getInstance().build("sms.properties", "sms.aliyun");
    }


    @Test
    public void send1() throws Exception {
        Assert.assertEquals(SmsStatus.Success, mSms.send("15805903814", "6666"));
    }

    @Test
    public void send2() throws Exception {
        boolean bool = mSms.send("18750936896", "8888") == SmsStatus.Success;
        Thread.sleep(5000);
        bool = bool && mSms.send("18750936896", "7777") == SmsStatus.Success;
        Assert.assertEquals(true, bool);
    }

}
