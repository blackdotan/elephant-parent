package com.ipukr.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 不支持操作 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/5/9.
 */
public class UnsupportedOperationEx extends AbstractEx {

    public UnsupportedOperationEx(String message) {
        super(message);
    }

    public UnsupportedOperationEx(HttpStatus status, String message) {
        super(status, message);
    }

    public UnsupportedOperationEx(Throwable cause) {
        super(cause);
    }

    public UnsupportedOperationEx(Throwable cause, String message) {
        super(cause, message);
    }

    public UnsupportedOperationEx(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }
}
