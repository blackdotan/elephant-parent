package com.ipukr.elephant.cloud.storage.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/4/12.
 */
@Data
@AllArgsConstructor
public abstract class UploadResponse {

    /**
     * 是否成功
     */
    protected boolean success;

    /**
     * 文件名
     */
    protected String filename;

    /**
     * 文件哈希值
     */
    protected String hash;

    /**
     * 文件key值
     */
    protected String key;

    /**
     * 域名
     */
    protected String domain;

    /**
     * 前缀
     */
    protected String prefix;

    /**
     * 后缀
     */
    protected String subfix;

    /**
     * URL地址
     */
    protected String url;

}
