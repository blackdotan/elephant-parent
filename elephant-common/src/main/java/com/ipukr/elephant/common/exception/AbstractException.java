package com.ipukr.elephant.common.exception;

import com.ipukr.elephant.utils.StringUtils;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/6.
 */
public abstract class AbstractException extends Exception {

    private String code = "E000X";

    private String message;

    public AbstractException(Throwable cause) {
        super(cause);
    }

    public AbstractException(String message) {
        super(message);
        this.message = message;
    }

    public AbstractException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    public AbstractException(Throwable cause, String code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        String error = StringUtils.easyAppend("{}, {}", code, message);
        return error;
    }

}
