package com.ipukr.elephant.common.exception;

import org.springframework.http.HttpStatus;

/**
 * SQL语句异常 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/14.
 */
@Deprecated
public class SQLException extends AbstractEx {

    public SQLException(String message) {
        super(message);
    }

    public SQLException(HttpStatus status, String message) {
        super(status, message);
    }

    public SQLException(Throwable cause) {
        super(cause);
    }

    public SQLException(Throwable cause, String message) {
        super(cause, message);
    }

    public SQLException(Throwable cause, HttpStatus status, String message) {
        super(cause, status, message);
    }
}