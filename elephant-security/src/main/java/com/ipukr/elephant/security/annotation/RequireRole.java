package com.ipukr.elephant.security.annotation;

import java.lang.annotation.*;

/**
 * 需要角色
 * Created by ryan on 上午10:21.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireRole {
    /**
     * 角色名称
     * @return
     */
    String[] value() default {};
}
