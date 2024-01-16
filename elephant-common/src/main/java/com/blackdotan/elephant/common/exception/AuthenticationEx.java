package com.blackdotan.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 *
 * 401 Unauthorized - [*]：认证异常（Token不存在、用户未登录、账号密码错误） <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/14.
 */
public class AuthenticationEx extends AbstractEx {

    public AuthenticationEx(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }

    public AuthenticationEx(String message, Object... args) {
        super(HttpStatus.UNAUTHORIZED, message, args);
    }

    public AuthenticationEx(HttpStatus status, String message) {
        super(status, message);
    }

    public AuthenticationEx(HttpStatus status, String message, Object... args) {
        super(status, message, args);
    }

    public AuthenticationEx(Throwable cause) {
        super(cause);
    }

    public AuthenticationEx(Throwable cause, String message) {
        super(cause, message);
    }

    public AuthenticationEx(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }

    public AuthenticationEx(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }

    public AuthenticationEx(Throwable cause, HttpStatus status, String message, Object... args) {
        super(cause, status, message, args);
    }
}
