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
public abstract class AbstractException extends Exception {

    private HttpStatus status;

    private HttpHeaders headers = new HttpHeaders();

    public AbstractException(String message) {
        super(message);
        headers.add("msg", message);
    }

    public AbstractException(HttpStatus status, String message) {
        status = status;
        headers.add("msg", message);
    }

    public AbstractException(Throwable cause) {
        super(cause);
    }

    public AbstractException(Throwable cause, String message) {
        super(cause);
        headers.add("msg", message);
    }

    public AbstractException(Throwable cause, HttpStatus status, String message) {
        super(cause);
        status = status;
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
