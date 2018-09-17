package com.ipukr.elephant.common.exception;

/**
 * 非法状态 <br>
 *
 * @author ryan.
 * <p>
 * Created by ryan on 2018/1/22.
 */
public class IllegalStateEx extends AbstractException {

    public IllegalStateEx(Throwable cause) {
        super(cause);
    }

    public IllegalStateEx(String message) {
        super(message);
    }

    public IllegalStateEx(String message, String... args) {
        super(message, args);
    }

    public IllegalStateEx(Throwable cause, String message) {
        super(cause, message);
    }

    public IllegalStateEx(Throwable cause, String message, String... args) {
        super(cause, message, args);
    }

    public IllegalStateEx(Throwable cause, Integer code, String message) {
        super(cause, code, message);
    }

    public IllegalStateEx(Throwable cause, Integer code, String message, String... args) {
        super(cause, code, message, args);
    }
}
