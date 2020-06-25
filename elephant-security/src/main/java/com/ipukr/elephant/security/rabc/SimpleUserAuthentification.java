package com.ipukr.elephant.security.rabc;

import com.ipukr.elephant.security.Authentification;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
	 * 客户端类型
	 */
	@Deprecated
	private int type;
	// ====================================[用户]====================================
	/**
	 * User.Id
	 */
	private Integer id;

	/**
	 * User.Eno
	 */
	private String key;
	// ====================================[机构]====================================
	/**
	 * 所属机构 Organization.Id
	 * eg：组织机构代码 GB
	 */
	private Integer organizationId;
	/**
	 * 所属机构 Organization.Name
	 */
	private String organizationName;
	/**
	 * 行政区划代码
	 */
	private Integer administrativeDivisionId;
	// ====================================[部门]====================================
	/**
	 * 所属部门 Department.Id
	 * eg：部门代码
	 */
	private Integer departmentId;
	/**
	 * 所属部门 Department.Name
	 */
	private String departmentName;

	// ====================================[权限]====================================
	/**
	 * 授权机构（该用户可以访问的机构数据，[Organization.Id...] ）
	 */
	private List<String> subordinates = new ArrayList<String>();

	/**
	 * 授权角色（该用户持有的机构数据，[Role.Name...]）
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
