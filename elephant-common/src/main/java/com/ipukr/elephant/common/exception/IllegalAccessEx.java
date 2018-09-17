package com.ipukr.elephant.common.exception;

/**
 * 非法访问 <br>
 *
 * @author ryan.
 * <p>
 * Created by ryan on 2018/1/22.
 */
public class IllegalAccessEx extends AbstractException {

    public IllegalAccessEx(Throwable cause) {
        super(cause);
    }

    public IllegalAccessEx(String message) {
        super(message);
    }

    public IllegalAccessEx(String message, String... args) {
        super(message, args);
    }

    public IllegalAccessEx(Throwable cause, String message) {
        super(cause, message);
    }

    public IllegalAccessEx(Throwable cause, String message, String... args) {
        super(cause, message, args);
    }

    public IllegalAccessEx(Throwable cause, Integer code, String message) {
        super(cause, code, message);
    }

    public IllegalAccessEx(Throwable cause, Integer code, String message, String... args) {
        super(cause, code, message, args);
    }
}
