package com.ipukr.elephant.common.exception;

import com.ipukr.elephant.utils.StringUtils;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/**
 * 请描述类 <br>
 *
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/6.
 */
public abstract class AbstractEx extends RuntimeException {

    private HttpStatus status;

    private HttpHeaders headers = new HttpHeaders();

    public AbstractEx(String message) {
        super(message);
        headers.add("msg", message);
    }

    public AbstractEx(String message, String...args) {
        super(MessageFormatter.format(message, args).getMessage());
        headers.add("msg", MessageFormatter.format(message, args).getMessage());
    }

    public AbstractEx(HttpStatus status, String message) {
        super(message);
        this.status = status;
        headers.add("msg", message);
    }

    public AbstractEx(HttpStatus status, String message, String...args) {
        super(MessageFormatter.format(message, args).getMessage());
        this.status = status;
        headers.add("msg", MessageFormatter.format(message, args).getMessage());
    }

    @Deprecated
    public AbstractEx(Throwable cause) {
        super(cause);
    }

    @Deprecated
    public AbstractEx(Throwable cause, String message) {
        super(cause);
        headers.add("msg", message);
    }

    @Deprecated
    public AbstractEx(Throwable cause, String message, String...args) {
        super(cause);
        headers.add("msg", MessageFormatter.format(message, args).getMessage());
    }

    @Deprecated
    public AbstractEx(Throwable cause, HttpStatus status, String message) {
        super(cause);
        this.status = status;
        headers.add("msg", message);
    }

    @Deprecated
    public AbstractEx(Throwable cause, HttpStatus status, String message, String...args) {
        super(cause);
        this.status = status;
        headers.add("msg", MessageFormatter.format(message, args).getMessage());
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }
}
