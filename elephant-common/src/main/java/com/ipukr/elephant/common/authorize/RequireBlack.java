package com.ipukr.elephant.common.authorize;

import java.lang.annotation.*;

/**
 * 黑名单
 * Created by ryan on 上午11:06.
 * @see @see com.ipukr.ants.session.domain.RequireBlack
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Deprecated
public @interface RequireBlack {
}
