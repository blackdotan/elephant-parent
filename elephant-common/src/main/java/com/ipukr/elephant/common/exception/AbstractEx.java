package com.ipukr.elephant.common.exception;

import com.ipukr.elephant.utils.StringUtils;
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

    public AbstractEx(HttpStatus status, String message) {
        this.status = status;
        headers.add("msg", message);
    }

    public AbstractEx(Throwable cause) {
        super(cause);
    }

    public AbstractEx(Throwable cause, String message) {
        super(cause);
        headers.add("msg", message);
    }

    public AbstractEx(Throwable cause, HttpStatus status, String message) {
        super(cause);
        this.status = status;
        headers.add("msg", message);
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
