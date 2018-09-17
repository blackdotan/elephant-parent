package com.ipukr.elephant.common.exception;

/**
 * 权限异常 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/14.
 */
public class AuthorizeEx extends AbstractException {

    public AuthorizeEx(Throwable cause) {
        super(cause);
    }

    public AuthorizeEx(String message) {
        super(message);
    }

    public AuthorizeEx(String message, String... args) {
        super(message, args);
    }

    public AuthorizeEx(Throwable cause, String message) {
        super(cause, message);
    }

    public AuthorizeEx(Throwable cause, String message, String... args) {
        super(cause, message, args);
    }

    public AuthorizeEx(Throwable cause, Integer code, String message) {
        super(cause, code, message);
    }

    public AuthorizeEx(Throwable cause, Integer code, String message, String... args) {
        super(cause, code, message, args);
    }
}
