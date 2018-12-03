package com.ipukr.elephant.mail.config;

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
public class SSLMailSenderConfig {

    private String host;
    private Integer port;
    private String protocol;
    private Boolean auth;
    private String username;
    private String password;
    private Boolean debug = false;

    private SSLMailSenderConfig(Builder builder) {
        setHost(builder.host);
        setPort(builder.port);
        setProtocol(builder.protocol);
        setAuth(builder.auth);
        setUsername(builder.username);
        setPassword(builder.password);
        setDebug(builder.debug);
    }

    public static Builder custom() {
        return new Builder();
    }

    public static final class Builder {
        private String host;
        private Integer port;
        private String protocol;
        private Boolean auth;
        private String username;
        private String password;
        private Boolean debug;

        private Builder() {
        }

        public Builder host(String val) {
            host = val;
            return this;
        }

        public Builder port(Integer val) {
            port = val;
            return this;
        }

        public Builder protocol(String val) {
            protocol = val;
            return this;
        }

        public Builder auth(Boolean val) {
            auth = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder debug(Boolean val) {
            debug = val;
            return this;
        }

        public SSLMailSenderConfig build() {
            return new SSLMailSenderConfig(this);
        }
    }
}
