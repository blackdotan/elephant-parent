package com.ipukr.elephant.push.backend;

import com.ipukr.elephant.architecture.factory.Factory;
import com.ipukr.elephant.push.IPush;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by ryan on 下午5:43.
 */
public class GetuiIMPushTest{
    private IPush mIPush;

    @Before
    public void init() throws Exception {
        mIPush = Factory.getInstance().build();
    }

    @Test
    public void testSingle() {
        mIPush.push("5be56edb65387415253b05011668a0ec", "你好吗");
    }

    public void testList() {
    }
}
