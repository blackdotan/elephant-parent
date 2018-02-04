package com.ipukr.elephant.common.exception;

/**
 * 认证异常 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/14.
 */
public class AuthenticationException extends AbstractException {

    public AuthenticationException(String message) {
        super(message);
    }


    public AuthenticationException(Throwable cause) {
        super(cause);
    }

    public AuthenticationException(Throwable cause, String message) {
        super(cause, message);
    }


    public AuthenticationException(Throwable cause, String code, String message) {
        super(cause, code, message);
    }


}