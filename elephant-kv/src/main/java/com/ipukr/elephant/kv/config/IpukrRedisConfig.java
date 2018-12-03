package com.ipukr.elephant.kv.config;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/9/4.
 */
@Getter
@Setter
@ToString
public class IpukrRedisConfig {

    private Integer maxTotal;
    private Integer maxIdle;
    private Integer maxWait;
    private Integer maxActive;
    private Boolean testOnBorrow;
    private Integer timeout;
    private String address;
    private Integer port;
    private String auth;
    private Integer db;

    private IpukrRedisConfig(Builder builder) {
        setMaxTotal(builder.maxTotal);
        setMaxIdle(builder.maxIdle);
        setMaxWait(builder.maxWait);
        setMaxActive(builder.maxActive);
        setTestOnBorrow(builder.testOnBorrow);
        setTimeout(builder.timeout);
        setAddress(builder.address);
        setPort(builder.port);
        setAuth(builder.auth);
        setDb(builder.db);
    }

    public final static Builder custom() {
        return new Builder();
    }

    public static final class Builder {
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

        public Builder() {
        }

        public Builder maxTotal(Integer val) {
            maxTotal = val;
            return this;
        }

        public Builder maxIdle(Integer val) {
            maxIdle = val;
            return this;
        }

        public Builder maxWait(Integer val) {
            maxWait = val;
            return this;
        }

        public Builder maxActive(Integer val) {
            maxActive = val;
            return this;
        }

        public Builder testOnBorrow(Boolean val) {
            testOnBorrow = val;
            return this;
        }

        public Builder timeout(Integer val) {
            timeout = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public Builder port(Integer val) {
            port = val;
            return this;
        }

        public Builder auth(String val) {
            auth = val;
            return this;
        }

        public Builder db(Integer val) {
            db = val;
            return this;
        }

        public IpukrRedisConfig build() {
            return new IpukrRedisConfig(this);
        }
    }
}
