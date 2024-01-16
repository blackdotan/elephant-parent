package com.blackdotan.elephant.http.third;

import com.blackdotan.elephant.http.config.HttpClientPoolConfig;
import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.InMemoryDnsResolver;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.net.Inet4Address;
import java.security.KeyStore;
import java.util.concurrent.TimeUnit;

/**
 * HttpClient线程池 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/29.
 */
//@Component
public class HttpClientPool {

    private SSLConnectionSocketFactory sslsf;
    private PoolingHttpClientConnectionManager manager;
    private RequestConfig mRequestConfig;
    private CredentialsProvider mCredentialsProvider;
    private InMemoryDnsResolver resolver = new InMemoryDnsResolver();

    private HttpClientPoolConfig config;

    /**
     * 构造方法
     * @param config
     * @throws Exception
     */
    public HttpClientPool(HttpClientPoolConfig config) throws Exception {
        this.config = config;
        this.init();
    }

    /**
     * 初始化
     * @throws Exception
     */
    private void init() throws Exception {
        String schema = config.getSchema();
        String protocol = config.getProtocol();
        String proxyHostname = config.getProxyHostname();
        Short proxyPort = config.getProxyPort();
        String proxyUsername = config.getProxyUsername();
        String proxyPassword = config.getProxyPassword();
        Integer timeout = config.getTimeout();
        Integer routeMax = config.getRouteMax();
        String dns = config.getDns();
        Integer connections = config.getConnections();

        if( manager == null ) {
            synchronized (this) {
                if(manager == null) {
                    // 初始化SSL, TLSv1 protocol
                    if (schema.equals("https")) {
                        SSLContextBuilder sslcontextbuilder = SSLContexts.custom();
                        if (config.getCert() != null) {
                            // 相信CA和所有自签名的证书
                            KeyStore trustStore = KeyStore.getInstance(config.getCerttype()==null?KeyStore.getDefaultType():config.getCerttype());
                            trustStore.load(config.getCert(), config.getCertpasswd());
                            sslcontextbuilder.loadTrustMaterial(trustStore, new TrustSelfSignedStrategy());

//                            sslcontextbuilder.setKeyManagerFactoryAlgorithm(KeyManagerFactory.getDefaultAlgorithm());

                            sslcontextbuilder.loadTrustMaterial(trustStore, new TrustSelfSignedStrategy());
                            sslcontextbuilder.setSecureRandom(new java.security.SecureRandom());
                        } else {
                            sslcontextbuilder.loadTrustMaterial(new TrustSelfSignedStrategy());
                        }
                        SSLContext sslcontext = sslcontextbuilder.build();

                        sslsf = new SSLConnectionSocketFactory(
                                sslcontext,
                                 protocol.split(","),
                                null,
                                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
                    }

                    manager = new PoolingHttpClientConnectionManager();
                    manager.setMaxTotal(connections);

                    // 设置连接配置
                    ConnectionConfig mConnectionConfig = ConnectionConfig.custom()
                            .setCharset(Consts.UTF_8)
                            .build();
                    manager.setDefaultConnectionConfig(mConnectionConfig);

                    // 设置路由配置
                    manager.setDefaultMaxPerRoute(routeMax);

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

    /**
     * 获取连接
     * @return
     */
    public CloseableHttpClient getConnection() {
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

}
