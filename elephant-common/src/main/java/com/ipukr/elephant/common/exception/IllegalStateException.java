package com.ipukr.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 非法状态 <br>
 *
 * @author ryan.
 * <p>
 * Created by ryan on 2018/1/22.
 */
public class IllegalStateException extends AbstractException {

    public IllegalStateException(String message) {
        super(message);
    }

    public IllegalStateException(HttpStatus status, String message) {
        super(status, message);
    }

    public IllegalStateException(Throwable cause) {
        super(cause);
    }

    public IllegalStateException(Throwable cause, String message) {
        super(cause, message);
    }

    public IllegalStateException(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }
}
