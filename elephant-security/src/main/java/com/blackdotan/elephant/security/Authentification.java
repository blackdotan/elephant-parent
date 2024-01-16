package com.blackdotan.elephant.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 认证 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/12/15.
 */
public abstract class Authentification<T extends Serializable> implements java.io.Serializable {


    /**
     * 获取认证关键字
     * @return
     */
	public abstract T getKey();

//    /**
//     * 授权列表
//     */
//    private List<? extends Authority> authorities = new ArrayList<>();
//
//    public abstract List<? extends Authority> getAuthorities();
//
//    public void setAuthorities(List<? extends Authority> authorities) {
//        this.authorities = authorities;
//    }

}
