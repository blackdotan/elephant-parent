package com.ipukr.elephant.security.annotation;

import java.lang.annotation.*;

/**
 * 需要Token
 * Created by ryan on 上午10:21.
 *
 * Spring使用方法:
 * HandlerMethod hm = (HandlerMethod) handler;
 * Method method = hm.getMethod();
 * if (method.isAnnotationPresent(RequireAccessToken.class)) {
 *      System.out.println("用户需要Token");
 * }
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireAccessToken {

}
