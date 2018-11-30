package com.ipukr.elephant.push.config;

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
@ConfigurationProperties(prefix = "ipukr.elephant.push.getui")
public class GetuiIMPushConfig {
    private String host;
    private String appid;
    private String appkey;
    private String master;
    private Boolean offline;
    private String sound;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public Boolean getOffline() {
        return offline;
    }

    public void setOffline(Boolean offline) {
        this.offline = offline;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    @Override
    public String toString() {
        return "GetuiIMPushConfig{" +
                "host='" + host + '\'' +
                ", appid='" + appid + '\'' +
                ", appkey='" + appkey + '\'' +
                ", master='" + master + '\'' +
                ", offline=" + offline +
                ", sound='" + sound + '\'' +
                '}';
    }
}
