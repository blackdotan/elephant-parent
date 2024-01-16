package com.blackdotan.elephant.security.annotation;

import java.lang.annotation.*;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/4/8.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Require {
    /**
     * JWT 需要验证 subject
     * @return
     */
    String[] subjects() default {};

    /**
     * JWT 验证 audience
     * @return
     */
    String[] audiences() default {};

    /**
     * 是否需要Token认证
     * @return
     */
    boolean accessToken() default true;

    /**
     * 是否需要角色认证
     * @return
     */
    String[] roles() default {};

    /**
     * 是否需要判断登录
     * @return
     */
    boolean login() default true;

    /**
     * 是否需要判断权限
     * @return
     */
    boolean permission() default false;

    /**
     * 是否需要授权
     * @return
     */
    boolean authority() default false;

}
