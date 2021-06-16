package com.blackdotan.elephant.security.annotation;

import java.lang.annotation.*;

/**
 * 需要授权 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/6/29.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireAuthorization {
	int value() default 1;
}
