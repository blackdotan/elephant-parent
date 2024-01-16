package com.blackdotan.elephant.security;

/**
 * 授权对象 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/12/15.
 */
public abstract class Authority implements java.io.Serializable {

	/**
	 * 判断 Key 与 [授权对象] 是否匹配
	 * @param key 关键字
	 * @param args
	 * @return
	 */
	public abstract boolean match(String key, String...args);

	/**
	 * 判断 对象 与 [授权对象] 是否匹配
	 * @param authority
	 * @return
	 */
	public abstract boolean match(Authority authority);
}
