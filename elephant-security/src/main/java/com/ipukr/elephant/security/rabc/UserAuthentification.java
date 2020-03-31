package com.ipukr.elephant.security.rabc;

import com.ipukr.elephant.security.Authentification;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
public abstract class UserAuthentification<T extends Serializable> extends Authentification<T> {

	/**
	 * 机构认证
	 */
	protected List<? extends Authentification<? extends Serializable>> organizations = new ArrayList();
	/**
	 * 角色认证
	 */
	protected List<RoleAuthentification> roles = new ArrayList<RoleAuthentification>();


}
