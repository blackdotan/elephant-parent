package com.ipukr.elephant.common.authorize;

import java.lang.annotation.*;

/**
 * 需要登录
 * Created by ryan on 上午10:21.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Deprecated
public @interface RequireLogin {

}
