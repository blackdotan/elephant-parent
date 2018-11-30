package com.ipukr.elephant.http.third;

import com.ipukr.elephant.http.config.HttpClientPoolConfig;
import com.ipukr.elephant.http.third.utils.PCMHelper;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.URI;
import java.util.Date;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HttpClientPoolConfig.class, HttpClientPool.class})
@EnableConfigurationProperties
public class HttpClientPoolTest {

    @Autowired
    private HttpClientPool pool;

    @Test
    public void testName() throws Exception {

        URI URI = new URIBuilder()
                .setScheme("https")
                .setHost("www.baidu.com")
                .setPort(443)
                .setCharset(Consts.UTF_8)
                .build();
        HttpUriRequest request = RequestBuilder.get(URI)
                .build();

        HttpResponse response = pool.getConnection().execute(request);
        String content = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(content);

    }
}
