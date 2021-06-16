package com.blackdotan.elephant.dssclient.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2020/3/31.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DPSClientConfig {
	/**
	 * 中心服务器IP地址
	 */
	private String ip;
	/**
	 * 中心服务器端口
	 */
	private int port;
	/**
	 * 用户
	 */
	private String user;
	/**
	 * 密码
	 */
	private String pass;

}
