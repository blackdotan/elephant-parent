package com.ipukr.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 非法参数 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/14.
 */
public class IllegalArgumentEx extends AbstractEx {
    public IllegalArgumentEx(String message) {
        super(message);
    }

    public IllegalArgumentEx(String message, String... args) {
        super(message, args);
    }

    public IllegalArgumentEx(HttpStatus status, String message) {
        super(status, message);
    }

    public IllegalArgumentEx(HttpStatus status, String message, String... args) {
        super(status, message, args);
    }

    public IllegalArgumentEx(Throwable cause) {
        super(cause);
    }

    public IllegalArgumentEx(Throwable cause, String message) {
        super(cause, message);
    }

    public IllegalArgumentEx(Throwable cause, String message, String... args) {
        super(cause, message, args);
    }

    public IllegalArgumentEx(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }

    public IllegalArgumentEx(Throwable cause, HttpStatus status, String message, String... args) {
        super(cause, status, message, args);
    }
}
