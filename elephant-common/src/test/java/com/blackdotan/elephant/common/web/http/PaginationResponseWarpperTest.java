package com.blackdotan.elephant.common.web.http;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.blackdotan.elephant.utils.JsonUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaginationResponseWarpperTest {
    @Test
    public void test1() {
        List<String> arrs = Arrays.asList(new String[]{ "1", "2"});
        Paginator paginator = new Paginator(1, 10, 120);
        List iPageList = new PageList(arrs, paginator);
        System.out.println(JsonUtils.parserObj2String(PaginationResponseWrapper.ok().body(iPageList)));
    }

    @Test
    public void test2() {
        List<String> arrs = Arrays.asList(new String[]{ "1", "2"});
        Paginator paginator = new Paginator(1, 10, 120);
        List<?> iPageList = new PageList(arrs, paginator);
        Map<String, Object> atta = new HashMap<String, Object>(){{
            put("msg", "hello this is atta");
        }};
        PaginationResponseWrapper wrapper = PaginationResponseWrapper.ok().data(iPageList).atta(atta).build();
        System.out.println(JsonUtils.parserObj2String(wrapper));
    }
}
