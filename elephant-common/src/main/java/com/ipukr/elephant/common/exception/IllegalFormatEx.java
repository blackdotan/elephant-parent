package com.ipukr.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 非法格式 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/1/31.
 */
public class IllegalFormatEx extends AbstractEx {

    public IllegalFormatEx(String message) {
        super(message);
    }

    public IllegalFormatEx(HttpStatus status, String message) {
        super(status, message);
    }

    public IllegalFormatEx(Throwable cause) {
        super(cause);
    }

    public IllegalFormatEx(Throwable cause, String message) {
        super(cause, message);
    }

    public IllegalFormatEx(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }
}
