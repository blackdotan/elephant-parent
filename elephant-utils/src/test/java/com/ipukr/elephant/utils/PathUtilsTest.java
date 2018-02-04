package com.ipukr.elephant.utils;

import org.junit.Test;

/**
 * Created by ryan on 上午10:28.
 */
public class PathUtilsTest {

    @Test
    public void testCompatible() throws Exception {
        System.setProperty("os.name","win");
        System.out.println(PathUtils.compatible("/C:\\MM\\zk"));
        System.out.println(PathUtils.compatible("C:\\MM\\zk", "\\"));

    }


}
