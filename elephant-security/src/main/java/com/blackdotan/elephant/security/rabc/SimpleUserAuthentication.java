package com.blackdotan.elephant.security.rabc;

import com.blackdotan.elephant.security.Authentification;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

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
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class SimpleUserAuthentication extends Authentification<Serializable> {

	/**
	 * 客户端类型
	 */
	private Serializable client;

	/**
	 * 来源渠道
	 */
	private Serializable channel;

	///**
	// * User Identifer
	// */
	//private Serializable identifer;
	//
	///**
	// * User Key
	// */
	//private Serializable key;

	/**
	 * User Identifer
	 */
	private Serializable identifer;

	/**
	 * User Key
	 */
	private Serializable key;

	/**
	 * 授权机构（该用户持有的机构数据，数据格式：[Structure.Id...]）
	 */
	private List<? extends Serializable> subordinates = new ArrayList<>();
	/**
	 * 授权角色（该用户持有的角色，数据格式：[Role.Name...]）
	 */
	private List<? extends Serializable> roles = new ArrayList<String>();
	/**
	 * 授权权限（该用户持有的权限，数据格式：[{Access.Method, Access.Url}...]）
	 */
	private List<AccessAuthority> authorities = new ArrayList<AccessAuthority>();
	@Override
	public Serializable getKey() {
		return key;
	}

	public void setKey(Serializable key) {
		this.key = key;
	}

	public Serializable getClient() {
		return client;
	}

	public void setClient(Serializable client) {
		this.client = client;
	}

	public Serializable getChannel() {
		return channel;
	}

	public void setChannel(Serializable channel) {
		this.channel = channel;
	}

	public Serializable getIdentifer() {
		return identifer;
	}

	public void setIdentifer(Serializable identifer) {
		this.identifer = identifer;
	}
	public List<? extends Serializable> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<? extends Serializable> subordinates) {
		this.subordinates = subordinates;
	}

	public List<? extends Serializable> getRoles() {
		return roles;
	}

	public void setRoles(List<? extends Serializable> roles) {
		this.roles = roles;
	}

	public List<AccessAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<AccessAuthority> authorities) {
		this.authorities = authorities;
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
