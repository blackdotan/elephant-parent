package com.ipukr.elephant.security.utils;

import com.ipukr.elephant.security.Authentification;
import com.ipukr.elephant.security.Authority;

import java.io.Serializable;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/12/15.
 */
public class AuthentificationUtilities {

	/**
	 * 判断是否授权
	 * @param authentification
	 * @param authority
	 * @return
	 */
	public static boolean isauthorised(Authentification<? extends Serializable> authentification, Authority authority) {
		return authentification.getAuthorities().stream()
				.filter(
						e ->e.match(authority)
				).count() > 0;
	}


}
