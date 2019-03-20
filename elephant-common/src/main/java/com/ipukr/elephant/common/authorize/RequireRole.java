package com.ipukr.elephant.common.authorize;

import java.lang.annotation.*;

/**
 * 需要登录
 * Created by ryan on 上午10:21.
 * @see @see @see com.ipukr.ants.session.domain.RequireRole
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Deprecated
public @interface RequireRole {
    /**
     * 角色名称
     * @return
     */
    String[] value() default {};
}
