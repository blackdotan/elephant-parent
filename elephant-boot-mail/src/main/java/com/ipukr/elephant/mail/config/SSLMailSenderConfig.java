package com.ipukr.elephant.mail.config;

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
@ConfigurationProperties(prefix = "ipukr.elephant.mail.ssl")
public class SSLMailSenderConfig {
    private String host;
    private String protocol;
    private String auth;
    private String username;
    private String password;
    private Boolean debug = false;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    @Override
    public String toString() {
        return "SSLMailSenderConfig{" +
                "host='" + host + '\'' +
                ", protocol='" + protocol + '\'' +
                ", auth='" + auth + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", debug=" + debug +
                '}';
    }
}
