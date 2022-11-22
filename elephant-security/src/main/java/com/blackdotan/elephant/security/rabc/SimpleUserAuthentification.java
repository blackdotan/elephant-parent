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
@Deprecated
public class SimpleUserAuthentification extends Authentification<String> {

	/**
	 * 客户端类型
	 */
	@Deprecated
	private int type;
	// ====================================[用户信息]====================================
	/**
	 * User.Id
	 */
	private Integer id;

	/**
	 * User.Eno
	 */
	private String key;
	// ====================================[机构信息]====================================
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
	// ====================================[部门信息]====================================
	/**
	 * 所属部门 Department.Id
	 * eg：部门代码
	 */
	private Integer departmentId;
	/**
	 * 所属部门 Department.Name
	 */
	private String departmentName;

	// ====================================[授权情况]====================================
	/**
	 * 下级机构（该用户可以访问的机构数据，[Organization.Id...] ）
	 */
	private List<? extends Serializable> subordinates = new ArrayList<>();

	/**
	 * 授权机构
	 * 既下级机构 + 所属机构
	 */
	private List<? extends Serializable> organizations = new ArrayList<>();

	/**
	 * 检查机构
	 * 既 指定数据隶属的机构
	 * pass through http.header from client
	 */
	private List<? extends Serializable> inspections = new ArrayList<>();

	/**
	 * 授权角色（该用户持有的机构数据，[Role.Name...]）
	 */
	private List<? extends Serializable> roles = new ArrayList<String>();

	/**
	 * 授权权限
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
	 * 是否持有机构
	 * @param subordinate
	 * @return
	 */
	public boolean hasSubordinate(Serializable subordinate) {
		return subordinates.contains(subordinate);
	}

	/**
	 * 是否持有机构
	 * @param organization
	 * @return
	 */
	public boolean hasOrganization(Serializable organization) {
		return organizations.contains(organization);
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
