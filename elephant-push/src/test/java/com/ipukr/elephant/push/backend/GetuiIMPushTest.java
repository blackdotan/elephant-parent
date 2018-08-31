package com.ipukr.elephant.push.backend;

import com.beust.jcommander.internal.Lists;
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
//        mIPush.push("f074d50a39d197f978d47770081db449", "测试推送1003");
//        mIPush.push("8f2596f92137af2d8d4904b72e4c77bb", "测试推送1003");
        mIPush.push("7db50e64153e078a2e8bc398e43bd3c1", "测试推送1003");
        mIPush.push("637559faca63ef0f4241b6c3a80208f4", "测试推送1003");
        mIPush.push("4fc43677a800aab68da976a66bc70b7d", "测试推送1003");
    }

    public void testList() {

        mIPush.push(Lists.newArrayList(
                "8f2596f92137af2d8d4904b72e4c77bb",
                "7db50e64153e078a2e8bc398e43bd3c1",
                "637559faca63ef0f4241b6c3a80208f4"),
                "测试推送1004");
    }
}
