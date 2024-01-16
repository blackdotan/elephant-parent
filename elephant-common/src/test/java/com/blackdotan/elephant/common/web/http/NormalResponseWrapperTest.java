package com.blackdotan.elephant.common.web.http;

import com.blackdotan.elephant.utils.JsonUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author black an
 * @date 2023/10/27
 */
public class NormalResponseWrapperTest {
    @Test
    public void test1() {
        Map<String, Object> data = new HashMap<String, Object>(){{
            put("id", 1L);
            put("nickname", "Jack");
        }};

        NormalResponseWrapper wrapper = NormalResponseWrapper.ok().data(data).build();
        System.out.println(JsonUtils.parserObj2String(wrapper));
    }
    @Test
    public void test2() {
        Map<String, Object> data = new HashMap<String, Object>(){{
            put("id", 1L);
            put("nickname", "Jack");
        }};
        Map<String, Object> atta = new HashMap<String, Object>(){{
            put("msg", "hello this is atta");
        }};
        NormalResponseWrapper wrapper = NormalResponseWrapper.ok().data(data).atta(atta).build();
        System.out.println(JsonUtils.parserObj2String(wrapper));
    }
}
