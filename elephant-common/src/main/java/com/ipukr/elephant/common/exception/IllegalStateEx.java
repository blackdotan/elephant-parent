package com.ipukr.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 406 Not Acceptable - [GET]：非法状态（状态转移错误） <br>
 *
 * @author ryan.
 * <p>
 * Created by ryan on 2018/1/22.
 */
public class IllegalStateEx extends AbstractEx {

    public IllegalStateEx(String message) {
        super(HttpStatus.NOT_ACCEPTABLE, message);
    }

    public IllegalStateEx(String message, String... args) {

        super(HttpStatus.NOT_ACCEPTABLE, message, args);
    }

    public IllegalStateEx(HttpStatus status, String message) {
        super(status, message);
    }

    public IllegalStateEx(HttpStatus status, String message, String... args) {
        super(status, message, args);
    }

    @Deprecated
    public IllegalStateEx(Throwable cause) {
        super(cause);
    }

    @Deprecated
    public IllegalStateEx(Throwable cause, String message) {
        super(cause, message);
    }

    @Deprecated
    public IllegalStateEx(Throwable cause, String message, String... args) {
        super(cause, message, args);
    }

    @Deprecated
    public IllegalStateEx(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }

    @Deprecated
    public IllegalStateEx(Throwable cause, HttpStatus status, String message, String... args) {
        super(cause, status, message, args);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
