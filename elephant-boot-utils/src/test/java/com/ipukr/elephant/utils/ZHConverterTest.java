package com.ipukr.elephant.utils;

import org.junit.Test;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/2/8.
 */
public class ZHConverterTest {
    @Test
    public void convert() {
        String text = ZHConverter.getInstance(ZHConverter.SIMPLIFIED).convert("興奮生意");
        System.out.println(text);
    }
}
