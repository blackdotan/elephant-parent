package com.ipukr.elephant.security.annotation;

import java.lang.annotation.*;

/**
 * 需要登录
 * Created by ryan on 上午10:21.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireAudience {
    /**
     * 角色名称
     * @return
     */
    String[] value();
}
