package com.blackdotan.elephant.utils;

import com.blackdotan.elephant.utils.language.PinyinUtils;
import org.junit.Test;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p/>
 *         Created by ryan on 2017/10/31.
 */
public class PinyinUtilsTest {
    @Test
    public void testPinyin() throws Exception {
        System.out.println(PinyinUtils.pinyin("扁担"));
        System.out.println(PinyinUtils.pinyin("扁舟"));
    }
}
