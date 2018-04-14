package com.ipukr.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 非法访问 <br>
 *
 * @author ryan.
 * <p>
 * Created by ryan on 2018/1/22.
 */
public class IllegalAccessException extends AbstractException {

    public IllegalAccessException(String message) {
        super(message);
    }

    public IllegalAccessException(HttpStatus status, String message) {
        super(status, message);
    }

    public IllegalAccessException(Throwable cause) {
        super(cause);
    }

    public IllegalAccessException(Throwable cause, String message) {
        super(cause, message);
    }

    public IllegalAccessException(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }
}
