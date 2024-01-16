package com.blackdotan.elephant.http.third;

import com.blackdotan.elephant.http.config.HttpClientPoolConfig;
import org.apache.http.*;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/29.
 */
public class HttpClientPoolTest {

    private HttpClientPool pool;

    /**
     * 初始化配置
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        HttpClientPoolConfig config = new HttpClientPoolConfig.Builder()
                .schema("https")
                .build();
        pool = new HttpClientPool(config);
    }

    /**
     * 测试HTTP请求
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
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
