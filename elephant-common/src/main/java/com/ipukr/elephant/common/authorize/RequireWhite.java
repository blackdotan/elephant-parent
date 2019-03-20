package com.ipukr.elephant.common.authorize;

import java.lang.annotation.*;

/**
 * 白名单
 * Created by ryan on 上午11:06.
 * @see @see @see com.ipukr.ants.session.domain.RequireWhite
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Deprecated
public @interface RequireWhite {
}
