package com.ipukr.elephant.common.exception;

/**
 * 非法格式 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/1/31.
 */
public class IllegalFormatException extends AbstractException {
    public IllegalFormatException(Throwable cause) {
        super(cause);
    }

    public IllegalFormatException(String message) {
        super(message);
    }

    public IllegalFormatException(Throwable cause, String message) {
        super(cause, message);
    }

    public IllegalFormatException(Throwable cause, String code, String message) {
        super(cause, code, message);
    }
}
