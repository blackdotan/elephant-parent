package com.blackdotan.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 400 BAD REQUEST - [POST/PUT/PATCH]：非法格式  <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/1/31.
 */
public class IllegalFormatEx extends AbstractEx {

    public IllegalFormatEx(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public IllegalFormatEx(String message, Object... args) {
        super(HttpStatus.BAD_REQUEST, message, args);
    }

    public IllegalFormatEx(HttpStatus status, String message) {
        super(status, message);
    }

    public IllegalFormatEx(HttpStatus status, String message, Object... args) {
        super(status, message, args);
    }

    @Deprecated
    public IllegalFormatEx(Throwable cause) {
        super(cause);
    }

    @Deprecated
    public IllegalFormatEx(Throwable cause, String message) {
        super(cause, message);
    }

    @Deprecated
    public IllegalFormatEx(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }

    @Deprecated
    public IllegalFormatEx(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }

    @Deprecated
    public IllegalFormatEx(Throwable cause, HttpStatus status, String message, Object... args) {
        super(cause, status, message, args);
    }
}
