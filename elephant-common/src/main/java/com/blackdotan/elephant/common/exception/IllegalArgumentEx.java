package com.blackdotan.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 400 BAD REQUEST - [POST/PUT/PATCH]：非法参数 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/14.
 */
public class IllegalArgumentEx extends AbstractEx {
    public IllegalArgumentEx(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public IllegalArgumentEx(String message, String... args) {
        super(HttpStatus.BAD_REQUEST, message, args);
    }

    public IllegalArgumentEx(HttpStatus status, String message) {
        super(status, message);
    }

    public IllegalArgumentEx(HttpStatus status, String message, String... args) {
        super(status, message, args);
    }

    @Deprecated
    public IllegalArgumentEx(Throwable cause) {
        super(cause);
    }

    @Deprecated
    public IllegalArgumentEx(Throwable cause, String message) {
        super(cause, message);
    }

    @Deprecated
    public IllegalArgumentEx(Throwable cause, String message, String... args) {
        super(cause, message, args);
    }

    @Deprecated
    public IllegalArgumentEx(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }

    @Deprecated
    public IllegalArgumentEx(Throwable cause, HttpStatus status, String message, String... args) {
        super(cause, status, message, args);
    }
}
