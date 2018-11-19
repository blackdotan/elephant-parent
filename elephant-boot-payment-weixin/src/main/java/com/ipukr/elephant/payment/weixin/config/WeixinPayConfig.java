package com.ipukr.elephant.payment.weixin.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/11/8.
 */
@Component
@ConfigurationProperties(prefix = "ipukr.elephant.payment.weixin")
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
}
