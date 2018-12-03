package com.ipukr.elephant.payment.alipay.config;

import lombok.Getter;
import lombok.Setter;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/9/6.
 */
@Getter
@Setter
public class AliPayConfig {
    /**
     * 支付宝网关（固定）
     */
    private String url;
    /**
     * APPID即创建应用后生成
     */
    private String appId;
    /**
     * 开发者应用私钥，由开发者自己生成
     */
    private String appPrivateKey ;
    /**
     * 开发者应用公钥
     */
    private String alipayPublicKey;
    /**
     * 参数返回格式，只支持json
     */
    private String format;
    /**
     * 请求和签名使用的字符编码格式，支持GBK和UTF
     */
    private String charset;
    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     */
    private String signType;
    /**
     * 回调地址
     * */
    private String notify;
    /**
     * 放大倍数
     */
    private Float magnification =  1.0F;

    private AliPayConfig(Builder builder) {
        setUrl(builder.url);
        setAppId(builder.appId);
        setAppPrivateKey(builder.appPrivateKey);
        setAlipayPublicKey(builder.alipayPublicKey);
        setFormat(builder.format);
        setCharset(builder.charset);
        setSignType(builder.signType);
        setNotify(builder.notify);
        setMagnification(builder.magnification);
    }

    public static Builder custom() {
        return new Builder();
    }


    public static final class Builder {
        private String url;
        private String appId;
        private String appPrivateKey;
        private String alipayPublicKey;
        private String format;
        private String charset;
        private String signType;
        private String notify;
        private Float magnification;

        private Builder() {
        }

        public Builder url(String val) {
            url = val;
            return this;
        }

        public Builder appId(String val) {
            appId = val;
            return this;
        }

        public Builder appPrivateKey(String val) {
            appPrivateKey = val;
            return this;
        }

        public Builder alipayPublicKey(String val) {
            alipayPublicKey = val;
            return this;
        }

        public Builder format(String val) {
            format = val;
            return this;
        }

        public Builder charset(String val) {
            charset = val;
            return this;
        }

        public Builder signType(String val) {
            signType = val;
            return this;
        }

        public Builder notify(String val) {
            notify = val;
            return this;
        }

        public Builder magnification(Float val) {
            magnification = val;
            return this;
        }

        public AliPayConfig build() {
            return new AliPayConfig(this);
        }
    }
}
