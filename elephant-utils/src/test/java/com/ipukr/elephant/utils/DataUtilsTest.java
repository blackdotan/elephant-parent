package com.ipukr.elephant.utils;

import com.ipukr.elephant.utils.domain.A;
import com.ipukr.elephant.utils.domain.B;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 上午11:38.
 */
public class DataUtilsTest {

    @Test
    public void testName() throws Exception {
        A a = new A(true);
        List list = new ArrayList<>();
        list.add(a);
        List<B> bs = DataUtils.copyProperties(list, B.class);
        System.out.println(bs);


    }
}
