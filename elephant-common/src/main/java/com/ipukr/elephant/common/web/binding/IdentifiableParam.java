package com.ipukr.elephant.common.web.binding;

import java.lang.annotation.*;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/5/21.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IdentifiableParam {
    /**
     * 用于绑定的请求参数名字
     */
    String value() default "";

    /**
     * 是否必须，默认是
     */
    boolean required() default false;
}
