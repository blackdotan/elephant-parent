package com.blackdotan.elephant.http.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.InputStream;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/9/4.
 */
//@Component
//@ConfigurationProperties(prefix = "ipukr.elephant.httpclient")
@Getter
@Setter
@ToString
public class HttpClientPoolConfig {

    private String schema;
    private String protocol;
    private String proxyHostname;
    private Short proxyPort;
    private String proxyUsername;
    private String proxyPassword;
    private Integer timeout;
    private Integer routeMax = 30;
    private String dns;
    private Integer connections;
    private InputStream cert;
    private String certtype;
    private char[] certpasswd;


    private HttpClientPoolConfig(Builder builder) {
        setSchema(builder.schema);
        setProtocol(builder.protocol);
        setProxyHostname(builder.proxyHostname);
        setProxyPort(builder.proxyPort);
        setProxyUsername(builder.proxyUsername);
        setProxyPassword(builder.proxyPassword);
        setTimeout(builder.timeout);
        setRouteMax(builder.routeMax);
        setDns(builder.dns);
        setConnections(builder.connections);
        setCert(builder.cert);
        setCerttype(builder.certtype);
        setCertpasswd(builder.certpasswd);
    }


    public final static Builder custom() {
        return new Builder();
    }

    public static final class Builder {
        private String schema = "https";
        private String protocol = "TLSv1,TLSv1.1,TLSv1.2";
        private String proxyHostname;
        private Short proxyPort;
        private String proxyUsername;
        private String proxyPassword;
        private Integer timeout = 20 * 1000;
        private Integer routeMax = 30;
        private String dns;
        private Integer connections = 5;
        private InputStream cert;
        private String certtype;
        private char[] certpasswd;

        public Builder() {
        }

        public Builder schema(String val) {
            schema = val;
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

        public Builder cert(InputStream val) {
            cert = val;
            return this;
        }

        public Builder certtype(String val) {
            certtype = val;
            return this;
        }

        public Builder cpasswd(char[] val) {
            certpasswd = val;
            return this;
        }

        public HttpClientPoolConfig build() {
            return new HttpClientPoolConfig(this);
        }
    }
}
