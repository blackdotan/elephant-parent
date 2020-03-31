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
public abstract class RoleAuthentification<T extends Serializable> extends Authentification<T> {

	private String key;
	/**
	 * 机构认证
	 */
	protected List<? extends Authentification<? extends Serializable>> organizations = new ArrayList();
	/**
	 * 角色认证
	 */
	protected List<? extends Authentification<? extends Serializable>> roles = new ArrayList();


}
