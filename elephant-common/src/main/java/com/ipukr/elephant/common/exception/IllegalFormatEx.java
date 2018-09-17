package com.ipukr.elephant.common.exception;

/**
 * 非法格式 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/1/31.
 */
public class IllegalFormatEx extends AbstractException {
    public IllegalFormatEx(Throwable cause) {
        super(cause);
    }

    public IllegalFormatEx(String message) {
        super(message);
    }

    public IllegalFormatEx(String message, String... args) {
        super(message, args);
    }

    public IllegalFormatEx(Throwable cause, String message) {
        super(cause, message);
    }

    public IllegalFormatEx(Throwable cause, String message, String... args) {
        super(cause, message, args);
    }

    public IllegalFormatEx(Throwable cause, Integer code, String message) {
        super(cause, code, message);
    }

    public IllegalFormatEx(Throwable cause, Integer code, String message, String... args) {
        super(cause, code, message, args);
    }
}
