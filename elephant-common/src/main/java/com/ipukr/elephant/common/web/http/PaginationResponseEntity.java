package com.ipukr.elephant.common.web.http;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/4/2.
 */
public class PaginationResponseEntity<T> extends HttpEntity {

    private HttpStatus statusCode;

    private T body;
    public PaginationResponseEntity(T body, HttpStatus statusCode) {
        this.body = body;
        this.statusCode = statusCode;
    }

    @Override
    public Object getBody() {
        PaginationResponseBody prb = new PaginationResponseBody();
        if(body instanceof PageList) {
            Paginator paginator = ((PageList) body).getPaginator();
            prb.setCount(paginator.getTotalCount());
            prb.setPageIndex(paginator.getPage());
            prb.setPageSize(paginator.getLimit());
            prb.setPageCount(paginator.getTotalPages());
            prb.setData(body);
        }
        return prb;
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

        public static <T> PaginationResponseEntity<T> ok(T body){
        return new PaginationResponseEntity<T>(body, HttpStatus.OK);
    }


    public static class BodyBuilder {

        private final HttpStatus status;


        public BodyBuilder(HttpStatus status) {
            this.status = status;
        }

        public <T> PaginationResponseEntity<T> body(T body) {
            return new PaginationResponseEntity<T>(body, this.status);
        }
    }


    private class PaginationResponseBody {
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
        private T data;

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

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

}
