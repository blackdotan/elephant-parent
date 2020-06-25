package com.ipukr.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 *
 * 403 Forbidden - [*]：权限异常（资源/功能未授权） <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/14.
 */
public class AuthorizeEx extends AbstractEx {

    public AuthorizeEx(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }

    public AuthorizeEx(String message, String... args) {
        super(HttpStatus.FORBIDDEN, message, args);
    }

    public AuthorizeEx(HttpStatus status, String message) {
        super(status, message);
    }

    public AuthorizeEx(HttpStatus status, String message, String... args) {
        super(status, message, args);
    }

    @Deprecated
    public AuthorizeEx(Throwable cause) {
        super(cause);
    }

    @Deprecated
    public AuthorizeEx(Throwable cause, String message) {
        super(cause, message);
    }

    @Deprecated
    public AuthorizeEx(Throwable cause, String message, String... args) {
        super(cause, message, args);
    }

    @Deprecated
    public AuthorizeEx(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }

    @Deprecated
    public AuthorizeEx(Throwable cause, HttpStatus status, String message, String... args) {
        super(cause, status, message, args);
    }
}
