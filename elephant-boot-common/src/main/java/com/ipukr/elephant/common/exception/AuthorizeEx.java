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
        super(message);
    }

    public AuthorizeEx(String message, String... args) {
        super(message, args);
    }

    public AuthorizeEx(HttpStatus status, String message) {
        super(status, message);
    }

    public AuthorizeEx(HttpStatus status, String message, String... args) {
        super(status, message, args);
    }

    public AuthorizeEx(Throwable cause) {
        super(cause);
    }

    public AuthorizeEx(Throwable cause, String message) {
        super(cause, message);
    }

    public AuthorizeEx(Throwable cause, String message, String... args) {
        super(cause, message, args);
    }

    public AuthorizeEx(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }

    public AuthorizeEx(Throwable cause, HttpStatus status, String message, String... args) {
        super(cause, status, message, args);
    }
}
