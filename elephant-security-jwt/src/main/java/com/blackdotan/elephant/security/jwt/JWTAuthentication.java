package com.blackdotan.elephant.security.jwt;

import com.blackdotan.elephant.security.rabc.SimpleUserAuthentification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2020/3/11.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JWTAuthentication extends SimpleUserAuthentification {

	public static final String NAME = "Authentication";

	/**
	 * 发行方
	 */
	private String issuer;
	/**
	 * 过期时间
	 */
	private Date exp;
	/**
	 * 主题
	 */
	private String subject;
	/**
	 * 受众
	 */
	private String[] audience;


}
