package com.ipukr.elephant.cloud.storage.config;

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
public class QiniuStorageConfig {

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String domain;

    public QiniuStorageConfig() {
    }

    private QiniuStorageConfig(Builder builder) {
        setAccessKey(builder.accessKey);
        setSecretKey(builder.secretKey);
        setBucket(builder.bucket);
        setDomain(builder.domain);
    }


    public final static Builder custom() {
        return new Builder();
    }

    public static final class Builder {
        private String accessKey;
        private String secretKey;
        private String bucket;
        private String domain;

        public Builder() {
        }

        public Builder accessKey(String val) {
            accessKey = val;
            return this;
        }

        public Builder secretKey(String val) {
            secretKey = val;
            return this;
        }

        public Builder bucket(String val) {
            bucket = val;
            return this;
        }

        public Builder domain(String val) {
            domain = val;
            return this;
        }

        public QiniuStorageConfig build() {
            return new QiniuStorageConfig(this);
        }
    }
}
