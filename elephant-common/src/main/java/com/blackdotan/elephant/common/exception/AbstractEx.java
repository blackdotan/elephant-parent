package com.blackdotan.elephant.common.exception;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/**
 * 业务异常基类 <br>
 *
 * 200 OK - [GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
 * 201 CREATED - [POST/PUT/PATCH]：用户新建或修改数据成功。
 * 202 Accepted - [*]：表示一个请求已经进入后台排队（异步任务）
 * 204 NO CONTENT - [DELETE]：用户删除数据成功。
 * 400 INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
 * 401 Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）。
 * 403 Forbidden - [*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
 * 404 NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
 * 406 Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
 * 410 Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。
 * 422 Unprocesable entity - [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误。
 * 500 INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功。
 * @author ryan.
 *         <p>
 *         Created by ryan on 2017/12/6.
 */
public abstract class AbstractEx extends RuntimeException {

    private HttpStatus status;

    private HttpHeaders headers = new HttpHeaders();

    private int code;

    public AbstractEx(String message) {
        super(message);
        headers.add("msg", message);
    }

    public AbstractEx(String message, Object...args) {
        super(MessageFormatter.format(message, args).getMessage());
        headers.add("msg", MessageFormatter.format(message, args).getMessage());
    }

    public AbstractEx(HttpStatus status, String message) {
        super(message);
        this.status = status;
        headers.add("msg", message);
    }

    public AbstractEx(HttpStatus status, String message, Object...args) {
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
    public AbstractEx(Throwable cause, String message, Object...args) {
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
    public AbstractEx(Throwable cause, HttpStatus status, String message, Object...args) {
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

    public int getCode() {
        return status.value();
    }

    public void setCode(int code) {
        this.code = code;
    }
}
