package com.ipukr.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 权限异常 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/14.
 */
public class AuthorizeEx extends AbstractEx {

    public AuthorizeEx(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }

    public AuthorizeEx(String message, String... args) {
        super(HttpStatus.UNAUTHORIZED, message, args);
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
