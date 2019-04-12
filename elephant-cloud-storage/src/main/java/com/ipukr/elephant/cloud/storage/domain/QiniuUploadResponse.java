package com.ipukr.elephant.cloud.storage.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/4/12.
 */
@Data
@Builder
public class QiniuUploadResponse {
    private boolean success;
    private String filename;
    private String hash;
    private String key;
}
