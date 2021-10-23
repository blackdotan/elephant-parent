package com.blackdotan.elephant.common.exception;

import org.springframework.http.HttpStatus;

public class IllegalStatusEx extends AbstractEx{

    private int code;

    public IllegalStatusEx(String message) {
        super(message);
    }

    public IllegalStatusEx(String message, String... args) {
        super(message, args);
    }

    public IllegalStatusEx(HttpStatus status, String message) {
        super(status, message);
    }

    public IllegalStatusEx(HttpStatus status, String message, String... args) {
        super(status, message, args);
    }

    public IllegalStatusEx(HttpStatus status, int code, String message, String... args) {
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
