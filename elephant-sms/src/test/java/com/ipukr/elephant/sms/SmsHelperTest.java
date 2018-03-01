package com.ipukr.elephant.sms;

import com.ipukr.elephant.architecture.factory.Factory;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by wmw on 12/13/16.
 */
public class SmsHelperTest {
    private Sms mSms;
    @Before
    public void init() throws Exception {
        mSms = Factory.getInstance().build("sms.properties");
    }


    @Test
    public void send1() throws Exception {
        Assert.assertEquals(SmsStatus.Success, mSms.send("15805903814", "6666"));
    }

    @Test
    public void send2() throws Exception {
        boolean bool = mSms.send("15805903814", "8888") == SmsStatus.Success;
        Thread.sleep(60000);
        bool = bool && mSms.send("15805903814", "7777") == SmsStatus.Success;
        Assert.assertEquals(true, bool);
    }
}
