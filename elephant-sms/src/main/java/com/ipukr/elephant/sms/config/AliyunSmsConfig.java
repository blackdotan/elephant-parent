package com.ipukr.elephant.sms.config;

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
public class AliyunSmsConfig {

    private String product;
    private String domain;
    private String accessKeyId;
    private String accessKeySecret;
    private String templateId;
    private String sign;

    private AliyunSmsConfig(Builder builder) {
        setProduct(builder.product);
        setDomain(builder.domain);
        setAccessKeyId(builder.accessKeyId);
        setAccessKeySecret(builder.accessKeySecret);
        setTemplateId(builder.templateId);
        setSign(builder.sign);
    }

    public final static Builder custom() {
        return new Builder();
    }

    public static final class Builder {
        private String product;
        private String domain;
        private String accessKeyId;
        private String accessKeySecret;
        private String templateId;
        private String sign;

        public Builder() {
        }

        public Builder product(String val) {
            product = val;
            return this;
        }

        public Builder domain(String val) {
            domain = val;
            return this;
        }

        public Builder accessKeyId(String val) {
            accessKeyId = val;
            return this;
        }

        public Builder accessKeySecret(String val) {
            accessKeySecret = val;
            return this;
        }

        public Builder templateId(String val) {
            templateId = val;
            return this;
        }

        public Builder sign(String val) {
            sign = val;
            return this;
        }

        public AliyunSmsConfig build() {
            return new AliyunSmsConfig(this);
        }
    }
}
