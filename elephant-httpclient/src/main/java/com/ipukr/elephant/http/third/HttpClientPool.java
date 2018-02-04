package com.ipukr.elephant.http.third;

import com.ipukr.elephant.architecture.AbstractAPI;
import com.ipukr.elephant.architecture.context.Context;
import com.sun.istack.internal.NotNull;
import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.InMemoryDnsResolver;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.net.Inet4Address;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/29.
 */
public class HttpClientPool extends AbstractAPI {

    private static final String SCHEMA  = "schema";
    private static final String HOSTNAME  = "hostname";
    private static final String PORT  = "port";
    private static final String PROTOCOL = "protocol";
    private static final String TIMEOUT = "timeout";
    private static final String CONNECTIONS = "connections";
    private static final String PROXY_HOSTNAME = "proxy.hostname";
    private static final String PROXY_PORT = "proxy.port";
    private static final String PROXY_USERNAME = "proxy.username";
    private static final String PROXY_PASSWORD = "proxy.password";
    private static final String ROUTE_MAX = "route.max";
    private static final String DNS =  "dns";

    private SSLConnectionSocketFactory sslsf;
    private PoolingHttpClientConnectionManager manager;
    private RequestConfig mRequestConfig;
    private CredentialsProvider mCredentialsProvider;
    private InMemoryDnsResolver resolver = new InMemoryDnsResolver();

    private String schema;
    private String hostname;
    private Short port;
    private String protocol;
    private String proxyHostname;
    private Short proxyPort;
    private String proxyUsername;
    private String proxyPassword;
    private Integer timeout;
    private Integer routeMax;
    private String dns;
    private Integer connections;

    public HttpClientPool(@NotNull Context context) throws Exception {
        super(context);
        this.schema = this.context.findStringAccordingKey(SCHEMA, "http");
        this.hostname = this.context.findStringAccordingKey(HOSTNAME);
        this.port = this.context.findNumberAccordingKey(PORT, 80).shortValue();
        this.protocol = this.context.findStringAccordingKey(PROTOCOL, "TLSv1,TLSv1.1,TLSv1.2");
        this.proxyHostname = this.context.findStringAccordingKey(PROXY_HOSTNAME);
        this.proxyPort = this.context.findNumberAccordingKey(PROXY_PORT, 80).shortValue();
        this.proxyUsername = this.context.findStringAccordingKey(PROXY_USERNAME);
        this.proxyPassword = this.context.findStringAccordingKey(PROXY_PASSWORD);
        this.timeout = this.context.findNumberAccordingKey(TIMEOUT, -1).intValue();
        this.routeMax = this.context.findNumberAccordingKey(ROUTE_MAX, 50).intValue();
        this.dns = this.context.findStringAccordingKey(DNS);
        this.connections = this.context.findNumberAccordingKey(CONNECTIONS, 20).intValue();
        this.init();
    }

    private HttpClientPool(@NotNull Builder builder) throws Exception {
        super(null);
        schema = builder.schema !=null ? builder.schema : "http";
        hostname = builder.hostname;
        port = builder.port!=null? builder.port : 80;
        protocol = builder.protocol!=null?builder.protocol: "TLSv1,TLSv1.1,TLSv1.2";
        proxyHostname = builder.proxyHostname;
        proxyPort = builder.proxyPort !=null ? builder.proxyPort: 80;
        proxyUsername = builder.proxyUsername;
        proxyPassword = builder.proxyPassword;
        timeout = builder.timeout!=null ? builder.timeout:-1;
        routeMax = builder.routeMax!=null ? builder.routeMax: 50;
        dns = builder.dns;
        connections = builder.connections!=null ? builder.connections: 20;
        this.init();
    }

    private void init() throws Exception {

        if( manager == null ) {
            synchronized (this) {
                if(manager == null) {
                    // 初始化SSL, TLSv1 protocol
                    if (schema.equals("https")) {
                        SSLContext sslcontext = SSLContexts.custom()
                                .loadTrustMaterial(new TrustSelfSignedStrategy())
                                .build();

                        sslsf = new SSLConnectionSocketFactory(
                                sslcontext,
                                protocol.split(","),
                                null,
                                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
                    }

                    manager = new PoolingHttpClientConnectionManager();
                    manager.setMaxTotal(connections);

                    HttpHost httphost = new HttpHost(hostname, port, schema);
                    // 设置连接配置
                    ConnectionConfig mConnectionConfig = ConnectionConfig.custom()
                            .setCharset(Consts.UTF_8)
                            .build();
                    manager.setConnectionConfig(httphost, mConnectionConfig);

                    // 设置路由配置
                    HttpRoute route = new HttpRoute(httphost);
                    manager.setMaxPerRoute(route, routeMax);

                    // 设置代理
                    RequestConfig.Builder rcb = RequestConfig.custom()
                            .setConnectTimeout(timeout)
                            .setSocketTimeout(timeout)
                            .setConnectionRequestTimeout(timeout);
                    if ( proxyHostname != null && !dns.equals("")) {
                        HttpHost proxy = new HttpHost(proxyHostname, proxyPort);
                        mCredentialsProvider = new BasicCredentialsProvider();
                        if (proxyUsername !=null) {
                            mCredentialsProvider.setCredentials(new AuthScope(proxy), new UsernamePasswordCredentials(proxyUsername, proxyPassword));
                        }
                        rcb.setProxy(proxy);
                    }
                    mRequestConfig = rcb.build();

                    if ( dns != null && !dns.equals("")) {
                        for(String hostport : dns.split(",")) {
                            String[] arr = hostport.split(":");
                            if(arr.length == 2) {
                                resolver.add(arr[0], Inet4Address.getByName(arr[1]));
                            }
                        }
                    }
                }
            }
        }
    }

    public HttpClient getConnection() {
        HttpClientBuilder builder = HttpClients.custom()
                .setConnectionManager(manager)
                .setDefaultRequestConfig(mRequestConfig)
                .setDnsResolver(resolver);

        if (sslsf != null) {
            builder.setSSLSocketFactory(sslsf);
        }

        if (mCredentialsProvider != null) {
           builder.setDefaultCredentialsProvider(mCredentialsProvider)
                   .setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy());
        }

        return builder.build();
    }

    public static Builder custome() {
        return new Builder();
    }

    public static final class Builder {

        private String schema;
        private String hostname;
        private Short port;
        private String protocol;
        private String proxyHostname;
        private Short proxyPort;
        private String proxyUsername;
        private String proxyPassword;
        private Integer timeout;
        private Integer routeMax;
        private String dns;
        private Integer connections;

        private Builder() {
        }


        public Builder schema(String val) {
            schema = val;
            return this;
        }

        public Builder hostname(String val) {
            hostname = val;
            return this;
        }

        public Builder port(Short val) {
            port = val;
            return this;
        }

        public Builder protocol(String val) {
            protocol = val;
            return this;
        }

        public Builder proxyHostname(String val) {
            proxyHostname = val;
            return this;
        }

        public Builder proxyPort(Short val) {
            proxyPort = val;
            return this;
        }

        public Builder proxyUsername(String val) {
            proxyUsername = val;
            return this;
        }

        public Builder proxyPassword(String val) {
            proxyPassword = val;
            return this;
        }

        public Builder timeout(Integer val) {
            timeout = val;
            return this;
        }

        public Builder routeMax(Integer val) {
            routeMax = val;
            return this;
        }

        public Builder dns(String val) {
            dns = val;
            return this;
        }

        public Builder connections(Integer val) {
            connections = val;
            return this;
        }

        public HttpClientPool build() throws Exception {
            return new HttpClientPool(this);
        }
    }

}
