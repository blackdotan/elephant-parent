package com.ipukr.elephant.kv.config;

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
@ConfigurationProperties(prefix = "ipukr.elephant.kv.redis")
public class IpukrRedisConfig {

    private Integer maxTotal = 300;
    private Integer maxIdle = 100;
    private Integer maxWait = 10;
    private Integer maxActive = 300;
    private Boolean testOnBorrow = false;
    private Integer timeout =  6000;
    private String address;
    private Integer port;
    private String auth;
    private Integer db;

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Integer getDb() {
        return db;
    }

    public void setDb(Integer db) {
        this.db = db;
    }

    @Override
    public String toString() {
        return "IpukrRedisConfig{" +
                "maxTotal=" + maxTotal +
                ", maxIdle=" + maxIdle +
                ", maxWait=" + maxWait +
                ", maxActive=" + maxActive +
                ", testOnBorrow=" + testOnBorrow +
                ", timeout=" + timeout +
                ", address='" + address + '\'' +
                ", port=" + port +
                ", auth='" + auth + '\'' +
                ", db=" + db +
                '}';
    }
}
