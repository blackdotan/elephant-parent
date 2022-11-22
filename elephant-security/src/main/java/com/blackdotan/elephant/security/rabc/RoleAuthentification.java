package com.blackdotan.elephant.security.rabc;

import com.blackdotan.elephant.security.Authentification;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class RoleAuthentification extends Authentification<String> {

	/**
	 * 关键字
	 */
	private String key;

	/**
	 * 授权机构
	 */
	private List<Authentification<? extends Serializable>> organizations = new ArrayList();


}
