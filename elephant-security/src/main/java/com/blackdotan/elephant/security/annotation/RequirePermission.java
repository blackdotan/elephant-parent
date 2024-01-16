package com.blackdotan.elephant.security.annotation;

import java.lang.annotation.*;

/**
 * 需要判断权限
 * @author black.an
 * Created by ryan on 上午10:21.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Deprecated
public @interface RequirePermission {

	/**
	 * 自定义拦截地址别名
	 * @return
	 */
	String uri() default "";

	/**
	 * 自定义拦截方法名
	 * @return
	 */
	String method() default "";
}
