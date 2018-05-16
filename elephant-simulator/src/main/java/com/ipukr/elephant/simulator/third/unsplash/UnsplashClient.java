package com.ipukr.elephant.simulator.third.unsplash;

import com.ipukr.elephant.http.third.HttpClientPool;
import com.ipukr.elephant.simulator.SimulatorHelper;
import com.ipukr.elephant.utils.DateUtils;
import com.ipukr.elephant.utils.JsonUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/5/1.
 */
public class UnsplashClient {

    private static final Logger logger = LoggerFactory.getLogger(UnsplashClient.class);

    public static final Integer MAX_SIZE = 30;
    private Integer clientId;
    private String access;

    private HttpClientPool pool = null;

    private List<String> pictures = new ArrayList<>();

    private UnsplashClient(Builder builder) throws Exception {
        clientId = builder.clientId;
        access = builder.access;
        this.init();
    }

    public static Builder custom() {
        return new Builder();
    }


    private void init() throws Exception {
        // HttpClientPool构建器
        pool = HttpClientPool.custom()
                .schema("https")
                .hostname("api.unsplash.com")
                .protocol("TLSv1,TLSv1.1,TLSv1.2")
                .timeout(20 * 1000)
                .connections(10)
                .build();
    }

    /**
     * 基于缓存机制
     * @param keyword
     * @param size 缓存大小
     * @return
     * @throws Exception
     */
    public List<String> cache(String keyword, int size) throws Exception {
        List<String> cache = new ArrayList<>();
        if (size > MAX_SIZE) {
            int count = 0;
            for (int i = 0; i < size / MAX_SIZE + 1; i++) {
                cache.addAll(collections(keyword, ++count, MAX_SIZE).regular());
            }
            logger.debug("请求接口次数 : count={}, hour={}", count, DateUtils.now("yyyy-MM-dd HH:mm"));
        } else {
            cache.addAll(collections(keyword, 1, size).regular());
        }
        pictures.addAll(cache);
        return cache;
    }

    /**
     * 基于缓存机制
     * @param size
     * @return
     * @throws Exception
     */
    public List<String> ncache(int size) throws Exception {
        return SimulatorHelper.random(pictures, size);
    }

    public UnsplashPicture random(String keyword) throws Exception {
        URI URI = new URIBuilder()
                .setScheme("https")
                .setHost("api.unsplash.com")
                .setPath("/photos/random")
                .setCharset(Consts.UTF_8)
                .build();

        HttpUriRequest request = RequestBuilder.get(URI)
                .setCharset(Consts.UTF_8)
                .addHeader("client_id", access)
                .addParameter("client_id", access)
                .addParameter("query", keyword)
                .build();

        HttpResponse response = pool.getConnection().execute(request);

        String result = EntityUtils.toString(response.getEntity());

        return JsonUtils.parserString2Obj(result, UnsplashPicture.class);
    }

    /**
     * @param keyword
     * @param pageIndex
     * @param pageSize /max 30
     * @return
     * @throws Exception
     */
    public UnsplashResult collections(String keyword, Integer pageIndex, Integer pageSize) throws Exception {
        URI URI = new URIBuilder()
                .setScheme("https")
                .setHost("api.unsplash.com")
                .setPath("/search/collections")
                .setCharset(Consts.UTF_8)
                .build();

        HttpUriRequest request = RequestBuilder.get(URI)
                .setCharset(Consts.UTF_8)
                .addHeader("client_id", access)
                .addParameter("client_id", access)
                .addParameter("query", keyword)
                .addParameter("page", pageIndex.toString())
                .addParameter("per_page", pageSize.toString())
                .build();

        HttpResponse response = pool.getConnection().execute(request);

        String result = EntityUtils.toString(response.getEntity(), "utf-8");

        return JsonUtils.parserString2Obj(result, UnsplashResult.class);
    }


    public static final class Builder {
        private Integer clientId;
        private String access;

        private Builder() {
        }

        public Builder clientId(Integer val) {
            clientId = val;
            return this;
        }

        public Builder access(String val) {
            access = val;
            return this;
        }

        public UnsplashClient build() throws Exception {
            return new UnsplashClient(this);
        }
    }
}
