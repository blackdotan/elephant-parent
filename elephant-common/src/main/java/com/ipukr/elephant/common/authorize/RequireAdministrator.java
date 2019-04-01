package com.ipukr.elephant.common.authorize;

import java.lang.annotation.*;

/**
 * 需要超级管理员权限
 * Created by ryan on 上午10:21.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireAdministrator {

}
