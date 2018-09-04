package com.ipukr.elephant.common.authorize;

import java.lang.annotation.*;

/**
 * 黑名单
 * Created by ryan on 上午11:06.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireBlack {
}
