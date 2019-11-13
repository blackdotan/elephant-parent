package com.ipukr.elephant.common.web.http;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

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

    private PaginationResponseBody body;

    /**
     * @param data
     * @param statusCode
     */
    public PaginationResponseEntity(List<T> data,
                                    HttpStatus statusCode) {
        this(data, true, "请求成功", new HttpHeaders(), statusCode);
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
        this(data, success, msg, new HttpHeaders(), statusCode);
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
        this(data, true, "请求成功", headers, statusCode);
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
                                    MultiValueMap<String, String> headers,
                                    HttpStatus statusCode) {
        body = new PaginationResponseBody();
        body.setData(data);
        body.setSuccess(success);
        body.setMsg(msg);
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

        private final HttpStatus status;

        private final HttpHeaders headers = new HttpHeaders();

        public BodyBuilder(HttpStatus status) {
            this.status = status;
        }

        public BodyBuilder header(String headerName, String... headerValues) {
            for (String headerValue : headerValues) {
                this.headers.add(headerName, headerValue);
            }
            return this;
        }

        public PaginationResponseEntity build() {
            return new PaginationResponseEntity(null, this.headers, this.status);
        }

        public PaginationResponseEntity body(List data) {
            return new PaginationResponseEntity(data, this.headers, this.status);
        }
    }


    public static class PaginationResponseBody {
        /**
         *
         */
        private String msg;
        /**
         *
         */
        private Boolean success;
        /**
         * 页面大小
         */
        private Integer pageSize = 0;

        /**
         * 页面游标
         */
        private Integer pageIndex = 0;

        /**
         * 页面数
         */
        private Integer pageCount = 0;

        /**
         * 记录总数
         */
        private Integer count = 0;

        /**
         * 数据
         * */
        private List data;

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Integer getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(Integer pageIndex) {
            this.pageIndex = pageIndex;
        }

        public Integer getPageCount() {
            return pageCount;
        }

        public void setPageCount(Integer pageCount) {
            this.pageCount = pageCount;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public List getData() {
            return data;
        }

        public void setData(List data) {
            this.data = data;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }
    }

}
