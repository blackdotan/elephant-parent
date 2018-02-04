package com.ipukr.elephant.common.exception;

/**
 * 权限异常 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/14.
 */
public class AuthorizeException extends AbstractException {

    public AuthorizeException(String message) {
        super(message);
    }

    public AuthorizeException(Throwable cause) {
        super(cause);
    }

    public AuthorizeException(Throwable cause, String message) {
        super(cause, message);
    }

    public AuthorizeException(Throwable cause, String code, String message) {
        super(cause, code, message);
    }
}
