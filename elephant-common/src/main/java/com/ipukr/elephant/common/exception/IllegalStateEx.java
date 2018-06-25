package com.ipukr.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 非法状态 <br>
 *
 * @author ryan.
 * <p>
 * Created by ryan on 2018/1/22.
 */
public class IllegalStateEx extends AbstractEx {

    public IllegalStateEx(String message) {
        super(message);
    }

    public IllegalStateEx(String message, String... args) {
        super(message, args);
    }

    public IllegalStateEx(HttpStatus status, String message) {
        super(status, message);
    }

    public IllegalStateEx(HttpStatus status, String message, String... args) {
        super(status, message, args);
    }

    public IllegalStateEx(Throwable cause) {
        super(cause);
    }

    public IllegalStateEx(Throwable cause, String message) {
        super(cause, message);
    }

    public IllegalStateEx(Throwable cause, String message, String... args) {
        super(cause, message, args);
    }

    public IllegalStateEx(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }

    public IllegalStateEx(Throwable cause, HttpStatus status, String message, String... args) {
        super(cause, status, message, args);
    }
}
