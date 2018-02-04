package com.ipukr.elephant.common.exception;

/**
 * 非法状态 <br>
 *
 * @author ryan.
 * <p>
 * Created by ryan on 2018/1/22.
 */
public class IllegalStateException extends AbstractException {

    public IllegalStateException(Throwable cause) {
        super(cause);
    }

    public IllegalStateException(String message) {
        super(message);
    }

    public IllegalStateException(Throwable cause, String message) {

        super(cause, message);
    }

    public IllegalStateException(Throwable cause, String code, String message) {
        super(cause, code, message);
    }
}
