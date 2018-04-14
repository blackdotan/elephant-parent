package com.ipukr.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 非法格式 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/1/31.
 */
public class IllegalFormatException extends AbstractException {

    public IllegalFormatException(String message) {
        super(message);
    }

    public IllegalFormatException(HttpStatus status, String message) {
        super(status, message);
    }

    public IllegalFormatException(Throwable cause) {
        super(cause);
    }

    public IllegalFormatException(Throwable cause, String message) {
        super(cause, message);
    }

    public IllegalFormatException(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }
}
