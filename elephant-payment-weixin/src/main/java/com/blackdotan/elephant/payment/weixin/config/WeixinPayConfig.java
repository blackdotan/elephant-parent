package com.blackdotan.elephant.payment.weixin.config;

import lombok.Getter;
import lombok.Setter;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/11/8.
 */
@Getter
@Setter
public class WeixinPayConfig {
    // 微信 appid
    private String appid;
    //
    private String mchid;
    // 回调地址
    private String notify;
    // 签名
    private String signature;
    // 证书地址
    private String certification;
    // 支付比例
    private Float magnification;

    private WeixinPayConfig(Builder builder) {
        setAppid(builder.appid);
        setMchid(builder.mchid);
        setNotify(builder.notify);
        setSignature(builder.signature);
        setCertification(builder.certification);
        setMagnification(builder.magnification);
    }

    public static Builder custom() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "WeixinConfig{" +
                "appid='" + appid + '\'' +
                ", mchid='" + mchid + '\'' +
                ", notify='" + notify + '\'' +
                ", signature='" + signature + '\'' +
                ", certification='" + certification + '\'' +
                ", magnification=" + magnification +
                '}';
    }


    public static final class Builder {
        private String appid;
        private String mchid;
        private String notify;
        private String signature;
        private String certification;
        private Float magnification;

        private Builder() {
        }

        public Builder appid(String val) {
            appid = val;
            return this;
        }

        public Builder mchid(String val) {
            mchid = val;
            return this;
        }

        public Builder notify(String val) {
            notify = val;
            return this;
        }

        public Builder signature(String val) {
            signature = val;
            return this;
        }

        public Builder certification(String val) {
            certification = val;
            return this;
        }

        public Builder magnification(Float val) {
            magnification = val;
            return this;
        }

        public WeixinPayConfig build() {
            return new WeixinPayConfig(this);
        }
    }
}
