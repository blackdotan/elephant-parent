package com.blackdotan.elephant.common.web.http;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表对象 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/4/2.
 */
public class PaginationResponseEntity<T> extends HttpEntity {

    private HttpStatus statusCode;

    private PaginationResponseEntityBody body;

    /**
     * @param data
     * @param statusCode
     */
    public PaginationResponseEntity(List<T> data,
                                    HttpStatus statusCode) {
        this(data, true, "请求成功", null, new HttpHeaders(), statusCode);
    }

    /**
     * @param data
     * @param success
     * @param msg
     * @param statusCode
     */
    public PaginationResponseEntity(List<T> data,
                                    Boolean success,
                                    String msg,
                                    HttpStatus statusCode) {
        this(data, success, msg, null, new HttpHeaders(), statusCode);
    }

    /**
     * @param headers
     * @param statusCode
     */
    public PaginationResponseEntity(MultiValueMap<String, String> headers,
                                    HttpStatus statusCode) {
        super(headers);
        this.statusCode = statusCode;
    }

    /**
     * @param data
     * @param headers
     * @param statusCode
     */
    public PaginationResponseEntity(List<T> data,
                                    MultiValueMap<String, String> headers,
                                    HttpStatus statusCode) {
        this(data, true, "请求成功", null, headers, statusCode);
    }

    /**
     * @param data
     * @param success
     * @param msg
     * @param headers
     * @param statusCode
     */
    public PaginationResponseEntity(List<T> data,
                                    Boolean success,
                                    String msg,
                                    Integer code,
                                    MultiValueMap<String, String> headers,
                                    HttpStatus statusCode) {
        body = new PaginationResponseEntityBody();
        body.setData(data);
        body.setSuccess(success);
        body.setMsg(msg);
        body.setCode(code == null ? statusCode.value() : code);
        if(data instanceof PageList) {
            Paginator paginator = ((PageList) data).getPaginator();
            body.setCount(paginator.getTotalCount());
            body.setPageIndex(paginator.getPage());
            body.setPageSize(paginator.getLimit());
            body.setPageCount(paginator.getTotalPages());
        }
        this.statusCode = statusCode;
    }


    @Override
    public Object getBody() {
        return body;
    }


    /**
     * Create a builder with the given status.
     * @param status the response status
     * @return the created builder
     * @since 4.1
     */
    public static BodyBuilder status(HttpStatus status) {
        return new BodyBuilder(status);
    }

    /**
     * Create a builder with the status set to {@linkplain HttpStatus#OK OK}.
     * @return the created builder
     * @since 4.1
     */
    public static BodyBuilder ok() {
        return new BodyBuilder(HttpStatus.OK);
    }

    /**
     * @param data
     * @param <T>
     * @return
     */
    public static <T> PaginationResponseEntity ok(List<T> data){
        return new PaginationResponseEntity(data, HttpStatus.OK);
    }



    public static class BodyBuilder {

        private HttpStatus status;

        private HttpHeaders headers = new HttpHeaders();

        /**
         * 提示信息
         */
        private String msg = "请求成功";
        /**
         * 是否成功
         */
        private Boolean success = true;
        /**
         * 提示信息
         */
        private Integer code;
        /**
         * 数据
         * */
        private List data;


        public BodyBuilder(HttpStatus status) {
            this.status = status;
        }

        public PaginationResponseEntity.BodyBuilder header(String headerName, String... headerValues) {
            for (String headerValue : headerValues) {
                this.headers.add(headerName, headerValue);
            }
            return this;
        }


        public PaginationResponseEntity.BodyBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public PaginationResponseEntity.BodyBuilder success(Boolean success) {
            this.success = success;
            return this;
        }

        public PaginationResponseEntity.BodyBuilder code(Integer code) {
            this.code = code;
            return this;
        }

        public PaginationResponseEntity.BodyBuilder data(List data) {
            this.data = data;
            return this;
        }

        public PaginationResponseEntity build() {
            return new PaginationResponseEntity(data, success, msg, code, this.headers, this.status);
        }

        public PaginationResponseEntity body(List data) {
            this.data = data;
            return new PaginationResponseEntity(data, success, msg, code, this.headers, this.status);
        }
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PaginationResponseEntityBody {
        /**
         * 提示信息
         */
        private String msg;
        /**
         * 是否成功
         */
        private Boolean success;
        /**
         * 提示信息
         */
        private Integer code;
        /**
         * 页面大小
         */
        @Builder.Default
        private Integer pageSize = 0;
        /**
         * 页面游标
         */
        @Builder.Default
        private Integer pageIndex = 0;
        /**
         * 页面数
         */
        @Builder.Default
        private Integer pageCount = 0;
        /**
         * 记录总数
         */
        @Builder.Default
        private Integer count = 0;
        /**
         * 数据
         * */
        @Builder.Default
        private List data = new ArrayList();

    }

}
