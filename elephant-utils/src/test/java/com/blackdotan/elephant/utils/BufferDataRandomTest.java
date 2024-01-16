package com.blackdotan.elephant.utils;

import org.apache.commons.collections.buffer.CircularFifoBuffer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/14.
 */
public class BufferDataRandomTest {

    @Test
    public void testName() throws Exception {
        List<String> pictures = new ArrayList<>();
        pictures.add("http://osjz61ph2.bkt.clouddn.com/gym_01.jpg");
        pictures.add("http://osjz61ph2.bkt.clouddn.com/gym_02.jpg");
        pictures.add("http://osjz61ph2.bkt.clouddn.com/gym_03.jpg");
        pictures.add("http://osjz61ph2.bkt.clouddn.com/gym_04.jpg");
        pictures.add("http://osjz61ph2.bkt.clouddn.com/gym_05.jpg");
        BufferDataRandom random = new BufferDataRandom<String>(pictures);
        List<String> rdms = random.random(5);
        System.out.println(JsonUtils.parserObj2String(rdms));

        CircularFifoBuffer buffer = new CircularFifoBuffer();
        buffer.add(1);
        buffer.isEmpty();
        buffer.remove();

    }
}
