package com.ipukr.elephant.security.annotation;

import com.ipukr.elephant.security.AbstractClientType;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/4/8.
 */
public @interface Require {
    /**
     * JWT 需要验证 subject
     * @return
     */
    String[] subjects();

    /**
     * JWT 需要验证 audience
     * @return
     */
    String[] audiences();
    /**
     * 是否需要Token认证
     * @return
     */
    boolean accessToken() default false;
    /**
     * 是否需要登录
     * @return
     */
    boolean login() default false;
    /**
     * 权限列表
     * @return
     */
    boolean permission() default false;

}
