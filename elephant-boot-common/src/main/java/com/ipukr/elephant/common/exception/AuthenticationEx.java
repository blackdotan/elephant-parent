package com.ipukr.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 认证异常 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/14.
 */
public class AuthenticationEx extends AbstractEx {

    public AuthenticationEx(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }

    public AuthenticationEx(String message, String... args) {
        super(HttpStatus.FORBIDDEN, message, args);
    }

    public AuthenticationEx(HttpStatus status, String message) {
        super(status, message);
    }

    public AuthenticationEx(HttpStatus status, String message, String... args) {
        super(status, message, args);
    }

    public AuthenticationEx(Throwable cause) {
        super(cause);
    }

    public AuthenticationEx(Throwable cause, String message) {
        super(cause, message);
    }

    public AuthenticationEx(Throwable cause, String message, String... args) {
        super(cause, message, args);
    }

    public AuthenticationEx(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }

    public AuthenticationEx(Throwable cause, HttpStatus status, String message, String... args) {
        super(cause, status, message, args);
    }
}