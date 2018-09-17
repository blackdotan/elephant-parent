package com.ipukr.elephant.common.exception;

/**
 * 非法参数 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/14.
 */
public class IllegalArgumentEx extends AbstractException {

    public IllegalArgumentEx(Throwable cause) {
        super(cause);
    }

    public IllegalArgumentEx(String message) {
        super(message);
    }

    public IllegalArgumentEx(String message, String... args) {
        super(message, args);
    }

    public IllegalArgumentEx(Throwable cause, String message) {
        super(cause, message);
    }

    public IllegalArgumentEx(Throwable cause, String message, String... args) {
        super(cause, message, args);
    }

    public IllegalArgumentEx(Throwable cause, Integer code, String message) {
        super(cause, code, message);
    }

    public IllegalArgumentEx(Throwable cause, Integer code, String message, String... args) {
        super(cause, code, message, args);
    }
}
