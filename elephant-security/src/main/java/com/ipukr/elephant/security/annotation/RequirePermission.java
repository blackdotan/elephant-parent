package com.ipukr.elephant.security.annotation;

import java.lang.annotation.*;

/**
 * 需要判断权限
 * Created by ryan on 上午10:21.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequirePermission {
	// 自定义拦截地址别名
	String uri() default "";
	// 自定义拦截方法名
	String method() default "";
}
