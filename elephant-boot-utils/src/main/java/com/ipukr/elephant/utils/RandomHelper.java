package com.ipukr.elephant.utils;

import java.util.Random;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/4/27.
 */
public class RandomHelper {

    /**
     * 随机数字
     * @param length
     * @return
     */
    public static String random(int length) {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<length; i++){
            buffer.append(random.nextInt(10));
        }
        return buffer.toString();
    }
}
