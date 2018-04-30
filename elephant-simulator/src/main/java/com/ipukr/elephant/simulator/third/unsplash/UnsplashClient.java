package com.ipukr.elephant.simulator.third.unsplash;

import com.ipukr.elephant.http.third.HttpClientPool;
import com.ipukr.elephant.utils.JsonUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/5/1.
 */
public class UnsplashClient {

    private Integer clientId;
    private String access;

    private HttpClientPool pool = null;

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
        HttpClientPool.Builder builder = HttpClientPool.custom()
                .schema("https")
                .hostname("api.unsplash.com")
                .protocol("TLSv1,TLSv1.1,TLSv1.2")
                .timeout(20 * 1000)
                .connections(10);

        // 创建HttpClientPool
        pool = builder.build();
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
