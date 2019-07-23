package com.ipukr.elephant.cloud.storage.domain;

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
public class QiniuUploadResponse extends UploadResponse {

	@Builder
	public QiniuUploadResponse(boolean success, String filename, String hash, String key, String domain, String prefix, String subfix, String url) {
		super(success, filename, hash, key, domain, prefix, subfix, url);
	}
}
