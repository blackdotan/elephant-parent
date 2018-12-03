package com.ipukr.elephant.push.config;


import lombok.Getter;
import lombok.Setter;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/9/4.
 */
@Getter
@Setter
public class GetuiIMPushConfig {

    private String host;
    private String appid;
    private String appkey;
    private String master;
    private Boolean offline;
    private String sound;

    private GetuiIMPushConfig(Builder builder) {
        setHost(builder.host);
        setAppid(builder.appid);
        setAppkey(builder.appkey);
        setMaster(builder.master);
        setOffline(builder.offline);
        setSound(builder.sound);
    }

    public final static Builder custom() {
        return new Builder();
    }

    public static final class Builder {
        private String host;
        private String appid;
        private String appkey;
        private String master;
        private Boolean offline;
        private String sound;

        public Builder() {
        }

        public Builder host(String val) {
            host = val;
            return this;
        }

        public Builder appid(String val) {
            appid = val;
            return this;
        }

        public Builder appkey(String val) {
            appkey = val;
            return this;
        }

        public Builder master(String val) {
            master = val;
            return this;
        }

        public Builder offline(Boolean val) {
            offline = val;
            return this;
        }

        public Builder sound(String val) {
            sound = val;
            return this;
        }

        public GetuiIMPushConfig build() {
            return new GetuiIMPushConfig(this);
        }
    }
}
