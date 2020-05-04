package com.ipukr.elephant.security.rabc;

import com.ipukr.elephant.security.Authentification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class SimpleUserAuthentification extends Authentification<String> {

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 关键字
	 */
	private String key;

	/**
	 * 客户端类型
	 */
	private int type;

	/**
	 * 授权机构（该用户可以访问的机构数据）
	 */
	private List<String> subordinates = new ArrayList<String>();

	/**
	 * 授权角色（该用户持有的机构数据）
	 */
	private List<String> roles = new ArrayList<String>();

	/**
	 * 授权权限（）
	 */
	private List<AccessAuthority> authorities = new ArrayList<AccessAuthority>();


	/**
	 * 是否持有角色
	 * @param role
	 * @return
	 */
	public boolean hasRole(String role) {
		return roles.contains(role);
	}

	/**
	 * 是否持有机构
	 * @param organization
	 * @return
	 */
	public boolean hasSubordinate(Integer organization) {
		return subordinates.contains(organization);
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
