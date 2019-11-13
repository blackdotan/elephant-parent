package com.ipukr.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 406 Not Acceptable - [GET]：不支持操作 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/5/9.
 */
public class UnsupportedOperationEx extends AbstractEx {

    public UnsupportedOperationEx(String message) {
        super(HttpStatus.NOT_ACCEPTABLE, message);
    }

    public UnsupportedOperationEx(String message, String... args) {
        super(HttpStatus.NOT_ACCEPTABLE,message, args);
    }

    public UnsupportedOperationEx(HttpStatus status, String message) {
        super(status, message);
    }

    public UnsupportedOperationEx(HttpStatus status, String message, String... args) {
        super(status, message, args);
    }

    @Deprecated
    public UnsupportedOperationEx(Throwable cause) {
        super(cause);
    }

    @Deprecated
    public UnsupportedOperationEx(Throwable cause, String message) {
        super(cause, message);
    }

    @Deprecated
    public UnsupportedOperationEx(Throwable cause, String message, String... args) {
        super(cause, message, args);
    }

    @Deprecated
    public UnsupportedOperationEx(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }

    @Deprecated
    public UnsupportedOperationEx(Throwable cause, HttpStatus status, String message, String... args) {
        super(cause, status, message, args);
    }
}
