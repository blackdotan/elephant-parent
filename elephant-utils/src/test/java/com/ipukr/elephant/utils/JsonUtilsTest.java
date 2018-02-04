package com.ipukr.elephant.utils;

import org.junit.Test;

/**
 * Created by wmw on 11/26/16.
 */
public class JsonUtilsTest {

    @Test
    public void testName() throws Exception {
        String json = "{\"uuid\":\"e1898842-3542-4249-b8b4-429c7d231c82\",\"owner\":\"f18872ef-7843-45d0-8af1-ce6b31f2ebfa\",\"city\":\"厦门市\",\"province\":\"福建省\",\"district\":\"同安区\",\"status\":3,\"address\":\"西柯镇丙洲村\",\"addresseeName\":\"陈伟仁\",\"addresseePhone\":\"13799737911\",\"lng\":10.0,\"lat\":10.0,\"updTime\":\"2016-11-21 14:44:40\",\"crtTime\":\"2016-11-21 14:44:40\",\"params\":{}}";
        ExpressAddress address = JsonUtils.parserString2Obj(json, ExpressAddress.class);
        System.out.println(JsonUtils.parserObj2String(address));
    }
}
