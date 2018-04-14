package com.ipukr.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 非法参数 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/14.
 */
public class IllegalArgumentException extends AbstractException {

    public IllegalArgumentException(String message) {
        super(message);
    }

    public IllegalArgumentException(HttpStatus status, String message) {
        super(status, message);
    }

    public IllegalArgumentException(Throwable cause) {
        super(cause);
    }

    public IllegalArgumentException(Throwable cause, String message) {
        super(cause, message);
    }

    public IllegalArgumentException(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }
}
