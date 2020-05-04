package com.ipukr.elephant.security.rabc;

import com.ipukr.elephant.security.Authority;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 访问授权 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/12/15.
 */
@Getter
@Setter
public class AccessAuthority extends Authority {
	/**
	 * 请求方法
	 */
	protected String method;
	/**
	 * 请求路径
	 */
	protected String url;

	/**
	 * @param key  关键字
	 * @param args
	 * @return
	 */
	@Override
	public boolean match(String key, String... args) {
		boolean bool = false;
		if (args.length == 1) {
			bool = this.method.equalsIgnoreCase(key)
					&& this.url.equalsIgnoreCase(args[0]);
		}
		return bool;
	}

	@Override
	@Deprecated
	public boolean match(Authority authority) {
		boolean bool = false;
		if (authority instanceof AccessAuthority) {
			String alimethod = ((AccessAuthority) authority).getMethod();
			String aliurl = ((AccessAuthority) authority).getUrl();
			bool = method != null && method.equalsIgnoreCase(alimethod)
					&& url != null && url.equalsIgnoreCase(aliurl);
		}

		return bool;
	}
}
