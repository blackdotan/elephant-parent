package com.blackdotan.elephant.security.rabc;

import com.blackdotan.elephant.security.Authentification;
import lombok.*;

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
public class UserAuthentification extends Authentification<String> {

	/**
	 * 主键
	 * @see #key 建议使用Key作为唯一
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
	private List<OrganizationAuthentification> subordinates = new ArrayList<OrganizationAuthentification>();

	/**
	 * 授权角色（该用户持有的机构数据）
	 */
	private List<RoleAuthentification> roles = new ArrayList<RoleAuthentification>();

	/**
	 * 授权权限（）
	 */
	private List<AccessAuthority> authorities = new ArrayList<AccessAuthority>();


	/**
	 * 是否持有角色
	 * @param role
	 * @return
	 */
	public boolean hasRole(RoleAuthentification role) {
		return roles.stream().filter(e->e.getKey().equals(role.getKey())).count() > 0;
	}

	/**
	 * 是否持有机构
	 * @param organization
	 * @return
	 */
	public boolean hasSubordinate(Authentification organization) {
		return subordinates.stream().filter(e->e.getKey().equals(organization.getKey())).count() > 0;
	}

	/**
	 * 是否持有权限
	 * @param authority
	 * @return
	 */
	@Deprecated
	public boolean hasAuthority(AccessAuthority authority) {
		return authorities.stream().filter(e->e.match(authority)).count() > 0;
	}
}
