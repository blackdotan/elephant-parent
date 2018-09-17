package com.ipukr.elephant.common.exception;

/**
 * 认证异常 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/14.
 */
public class AuthenticationEx extends AbstractException {

    public AuthenticationEx(Throwable cause) {
        super(cause);
    }

    public AuthenticationEx(String message) {
        super(message);
    }

    public AuthenticationEx(String message, String... args) {
        super(message, args);
    }

    public AuthenticationEx(Throwable cause, String message) {
        super(cause, message);
    }

    public AuthenticationEx(Throwable cause, String message, String... args) {
        super(cause, message, args);
    }

    public AuthenticationEx(Throwable cause, Integer code, String message) {
        super(cause, code, message);
    }

    public AuthenticationEx(Throwable cause, Integer code, String message, String... args) {
        super(cause, code, message, args);
    }
}