package com.blackdotan.elephant.security.annotation;

import java.lang.annotation.*;

/**
 * 白名单
 * Created by ryan on 上午11:06.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireWhite {
}
