package com.blackdotan.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 406 Not Acceptable - [GET]：不支持的状态 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/5/9.
 */
public class UnsupportedStateEx extends AbstractEx {

    public UnsupportedStateEx(String message) {
        super(HttpStatus.NOT_ACCEPTABLE, message);
    }

    public UnsupportedStateEx(String message, String... args) {
        super(HttpStatus.NOT_ACCEPTABLE, message, args);
    }

    public UnsupportedStateEx(HttpStatus status, String message) {
        super(status, message);
    }

    public UnsupportedStateEx(HttpStatus status, String message, String... args) {
        super(status, message, args);
    }

    @Deprecated
    public UnsupportedStateEx(Throwable cause) {
        super(cause);
    }

    @Deprecated
    public UnsupportedStateEx(Throwable cause, String message) {
        super(cause, message);
    }

    @Deprecated
    public UnsupportedStateEx(Throwable cause, String message, String... args) {
        super(cause, message, args);
    }

    @Deprecated
    public UnsupportedStateEx(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }

    @Deprecated
    public UnsupportedStateEx(Throwable cause, HttpStatus status, String message, String... args) {
        super(cause, status, message, args);
    }
}
