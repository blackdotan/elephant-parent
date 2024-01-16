package com.blackdotan.elephant.common.exception;

import org.springframework.http.HttpStatus;

import java.rmi.UnexpectedException;

/**
 * Unexpected Logic Exception / 流程逻辑不符期待异常
 *
 * @author ryan.
 * <p>
 * Created by ryan on 2018/1/22.
 */
public class UnexpectedLogicEx extends AbstractEx {

    private int code;

    public UnexpectedLogicEx(String message) {
        super(message);
    }

    public UnexpectedLogicEx(String message, Object... args) {
        super(message, args);
    }

    public UnexpectedLogicEx(HttpStatus status, String message) {
        super(status, message);
    }

    public UnexpectedLogicEx(HttpStatus status, String message, Object... args) {
        super(status, message, args);
    }

    public UnexpectedLogicEx(HttpStatus status, int code, String message, Object... args) {
        super(status, message, args);
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

}
