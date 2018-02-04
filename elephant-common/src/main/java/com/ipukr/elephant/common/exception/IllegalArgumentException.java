package com.ipukr.elephant.common.exception;

/**
 * 非法参数 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/14.
 */
public class IllegalArgumentException extends AbstractException {

    public IllegalArgumentException(String message) {
        super(message);
    }

    public IllegalArgumentException(Throwable cause) {
        super(cause);
    }

    public IllegalArgumentException(Throwable cause, String message) {
        super(cause, message);
    }

    public IllegalArgumentException(Throwable cause, String code, String message) {
        super(cause, code, message);
    }
}
