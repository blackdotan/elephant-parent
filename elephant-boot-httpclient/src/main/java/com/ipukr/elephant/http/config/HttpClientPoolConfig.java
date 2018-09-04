package com.ipukr.elephant.http.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/9/4.
 */
@Component
@ConfigurationProperties(prefix = "ipukr.elephant.httpclient")
public class HttpClientPoolConfig {

    private String schema;
    private String hostname;
    private Short port;
    private String protocol;
    private String proxyHostname;
    private Short proxyPort;
    private String proxyUsername;
    private String proxyPassword;
    private Integer timeout;
    private Integer routeMax = 30;
    private String dns;
    private Integer connections;

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Short getPort() {
        return port;
    }

    public void setPort(Short port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getProxyHostname() {
        return proxyHostname;
    }

    public void setProxyHostname(String proxyHostname) {
        this.proxyHostname = proxyHostname;
    }

    public Short getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Short proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyUsername() {
        return proxyUsername;
    }

    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getRouteMax() {
        return routeMax;
    }

    public void setRouteMax(Integer routeMax) {
        this.routeMax = routeMax;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public Integer getConnections() {
        return connections;
    }

    public void setConnections(Integer connections) {
        this.connections = connections;
    }

    @Override
    public String toString() {
        return "HttpClientPoolConfig{" +
                "schema='" + schema + '\'' +
                ", hostname='" + hostname + '\'' +
                ", port=" + port +
                ", protocol='" + protocol + '\'' +
                ", proxyHostname='" + proxyHostname + '\'' +
                ", proxyPort=" + proxyPort +
                ", proxyUsername='" + proxyUsername + '\'' +
                ", proxyPassword='" + proxyPassword + '\'' +
                ", timeout=" + timeout +
                ", routeMax=" + routeMax +
                ", dns='" + dns + '\'' +
                ", connections=" + connections +
                '}';
    }
}
