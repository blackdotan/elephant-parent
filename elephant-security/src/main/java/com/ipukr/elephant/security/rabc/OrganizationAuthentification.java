package com.ipukr.elephant.security.rabc;

import com.ipukr.elephant.security.Authentification;
import com.ipukr.elephant.security.Authority;
import lombok.*;

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
public class OrganizationAuthentification extends Authentification<String> {

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 关键字
	 */
	private String key;

	/**
	 * 授权机构（该用户可以访问的机构数据）
	 */
	private List<OrganizationAuthentification> subordinates = new ArrayList();

	/**
	 * 是否持有机构
	 * @param organization
	 * @return
	 */
	public boolean hasSubordinate(Authentification organization) {
		return subordinates.stream().filter(e->e.getKey().equals(organization.getKey())).count() > 0;
	}

}
