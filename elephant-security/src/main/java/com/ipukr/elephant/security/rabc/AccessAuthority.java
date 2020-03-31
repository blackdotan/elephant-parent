package com.ipukr.elephant.security.rabc;

import com.ipukr.elephant.security.Authority;

import java.io.Serializable;

/**
 * 访问授权 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/12/15.
 */
public abstract class AccessAuthority extends Authority {
	/**
	 * 请求方法
	 */
	protected String method;
	/**
	 * 请求路径
	 */
	protected String url;


}
