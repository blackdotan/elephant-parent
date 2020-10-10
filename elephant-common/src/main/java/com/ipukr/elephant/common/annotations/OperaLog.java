package com.ipukr.elephant.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperaLog {

    /**
     * @return
     */
    String type();

    /**
     * 操作
     * @return
     */
    String op();

    /**
     * 备注
     * @return
     */
    String remark() default "";

}
