package com.blackdotan.elephant.utils;

import org.junit.Test;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/4/25.
 */
public class StringUtilsTest {
    @Test
    public void testTo() {
        System.out.println(StringUtils.toUpperWithFirstCharacter("abd"));
        System.out.println(StringUtils.toUpperWithFirstCharacter("dbd"));
        System.out.println(StringUtils.toUpperWithFirstCharacter("zbd"));
        System.out.println(StringUtils.toUpperWithFirstCharacter("龙好"));
    }
}
