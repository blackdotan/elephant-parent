package com.ipukr.elephant.common.exception;

/**
 * 非法访问 <br>
 *
 * @author ryan.
 * <p>
 * Created by ryan on 2018/1/22.
 */
public class IllegalAccessException extends AbstractException {

    public IllegalAccessException(Throwable cause) {
        super(cause);
    }

    public IllegalAccessException(String message) {
        super(message);
    }

    public IllegalAccessException(Throwable cause, String message) {
        super(cause, message);
    }

    public IllegalAccessException(Throwable cause, String code, String message) {
        super(cause, code, message);
    }
}
