package com.ipukr.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/5/9.
 */
public class UnsupportedStateEx extends AbstractException {

    public UnsupportedStateEx(String message) {
        super(message);
    }

    public UnsupportedStateEx(HttpStatus status, String message) {
        super(status, message);
    }

    public UnsupportedStateEx(Throwable cause) {
        super(cause);
    }

    public UnsupportedStateEx(Throwable cause, String message) {
        super(cause, message);
    }

    public UnsupportedStateEx(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }
}
