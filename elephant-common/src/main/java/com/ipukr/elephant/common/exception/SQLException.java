package com.ipukr.elephant.common.exception;

/**
 * SQL语句异常 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/11/14.
 */
public class SQLException extends AbstractException {

    public SQLException(String message) {
        super(message);
    }

    public SQLException(Throwable cause) {
        super(cause);
    }

    public SQLException(Throwable cause, String message) {
        super(cause, AbstractExceptionConstant.SQL_ERROR_CODE, message);
    }

    public SQLException(Throwable cause, String code, String message) {
        super(cause, code, message);
    }
}