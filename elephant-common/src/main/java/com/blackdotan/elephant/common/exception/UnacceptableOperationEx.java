package com.blackdotan.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 406 Not Acceptable - [GET]：不支持的状态 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/5/9.
 */
public class UnacceptableOperationEx extends AbstractEx {

    public UnacceptableOperationEx(String message) {
        super(HttpStatus.NOT_ACCEPTABLE, message);
    }

    public UnacceptableOperationEx(String message, Object... args) {
        super(HttpStatus.NOT_ACCEPTABLE, message, args);
    }

    public UnacceptableOperationEx(HttpStatus status, String message) {
        super(status, message);
    }

    public UnacceptableOperationEx(HttpStatus status, String message, Object... args) {
        super(status, message, args);
    }

    @Deprecated
    public UnacceptableOperationEx(Throwable cause) {
        super(cause);
    }

    @Deprecated
    public UnacceptableOperationEx(Throwable cause, String message) {
        super(cause, message);
    }

    @Deprecated
    public UnacceptableOperationEx(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }

    @Deprecated
    public UnacceptableOperationEx(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }

    @Deprecated
    public UnacceptableOperationEx(Throwable cause, HttpStatus status, String message, Object... args) {
        super(cause, status, message, args);
    }
}
