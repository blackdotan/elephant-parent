package com.ipukr.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 非法访问 <br>
 *
 * @author ryan.
 * <p>
 * Created by ryan on 2018/1/22.
 */
public class IllegalAccessEx extends AbstractEx {

    public IllegalAccessEx(String message) {
        super(message);
    }

    public IllegalAccessEx(String message, String... args) {
        super(message, args);
    }

    public IllegalAccessEx(HttpStatus status, String message) {
        super(status, message);
    }

    public IllegalAccessEx(HttpStatus status, String message, String... args) {
        super(status, message, args);
    }

    public IllegalAccessEx(Throwable cause) {
        super(cause);
    }

    public IllegalAccessEx(Throwable cause, String message) {
        super(cause, message);
    }

    public IllegalAccessEx(Throwable cause, String message, String... args) {
        super(cause, message, args);
    }

    public IllegalAccessEx(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }

    public IllegalAccessEx(Throwable cause, HttpStatus status, String message, String... args) {
        super(cause, status, message, args);
    }
}
