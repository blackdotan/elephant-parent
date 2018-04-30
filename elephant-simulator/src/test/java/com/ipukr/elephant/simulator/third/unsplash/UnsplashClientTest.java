package com.ipukr.elephant.simulator.third.unsplash;

import com.ipukr.elephant.utils.JsonUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/5/1.
 */
public class UnsplashClientTest {
    private UnsplashClient client;

    @Before
    public void setUp() throws Exception {
        client = UnsplashClient.custom().clientId(25727).access("7fbfacbbb7e504c8d391adf6f023f9e947b89495d37e9aaf14c7094b6cd5234a").build();
    }

    @Test
    public void random() throws Exception {
        UnsplashPicture picture = client.random("people");
        System.out.println(picture.regular());
        System.out.println(JsonUtils.parserObj2String(picture));
    }


    @Test
    public void collections() throws Exception {
        List<String> pictures = client.collections("fruit apple", 1, 3).regular();
        for (String picture : pictures) {
            System.out.println(picture);
        }
    }

}
