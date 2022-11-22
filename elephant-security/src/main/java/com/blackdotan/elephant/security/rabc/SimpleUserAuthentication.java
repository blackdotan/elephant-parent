package com.blackdotan.elephant.security.rabc;

import com.blackdotan.elephant.security.Authentification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户认证 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/12/15.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserAuthentication extends Authentification<String> {

	/**
	 * 客户端类型
	 */
	private String client;

	/**
	 * User Identifer
	 */
	private Long identifer;

	/**
	 * User Key
	 */
	private String key;

	/**
	 * 授权机构（该用户持有的机构数据，[Structure.Id...]）
	 * pass through http.header from client
	 */
	private List<? extends Serializable> subordinates = new ArrayList<>();

	/**
	 * 授权角色（该用户持有的角色，[Role.Name...]）
	 */
	private List<? extends Serializable> roles = new ArrayList<String>();

	/**
	 * 授权权限（该用户持有的权限，[{Access.Method, Access.Url}...]）
	 */
	private List<AccessAuthority> authorities = new ArrayList<AccessAuthority>();

	/**
	 * 是否持有角色
	 * @param role
	 * @return
	 */
	public boolean hasRole(Serializable role) {
		return roles.contains(role);
	}

	/**
	 * 是否持有权限
	 * @param authority
	 * @return
	 */
	public boolean hasAuthority(AccessAuthority authority) {
		return authorities.stream().filter(e->e.match(authority)).count() > 0;
	}
}
