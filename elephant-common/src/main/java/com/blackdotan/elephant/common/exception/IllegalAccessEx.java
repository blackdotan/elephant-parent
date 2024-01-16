package com.blackdotan.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 403 Forbidden - [*]：非法访问（被操作数据未授权） <br>
 *
 * @author ryan.
 * <p>
 * Created by ryan on 2018/1/22.
 */
public class IllegalAccessEx extends AbstractEx {

    public IllegalAccessEx(String message) {
        super(HttpStatus.NOT_ACCEPTABLE, message);
    }

    public IllegalAccessEx(String message, Object... args) {
        super(HttpStatus.NOT_ACCEPTABLE, message, args);
    }

    public IllegalAccessEx(HttpStatus status, String message) {
        super(status, message);
    }

    public IllegalAccessEx(HttpStatus status, String message, Object... args) {
        super(status, message, args);
    }

    @Deprecated
    public IllegalAccessEx(Throwable cause) {
        super(cause);
    }

    @Deprecated
    public IllegalAccessEx(Throwable cause, String message) {
        super(cause, message);
    }

    @Deprecated
    public IllegalAccessEx(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }

    @Deprecated
    public IllegalAccessEx(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }

    @Deprecated
    public IllegalAccessEx(Throwable cause, HttpStatus status, String message, Object... args) {
        super(cause, status, message, args);
    }
}
