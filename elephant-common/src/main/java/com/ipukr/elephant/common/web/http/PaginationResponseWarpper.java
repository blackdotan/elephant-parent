package com.ipukr.elephant.common.web.http;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.List;

/****************************************************************
 * 分页对象封装，
 *
 * <p> 避免继承HttpEntity，以支持Feigin类型自动转换  <br>
 *
 * @author black.an wmw@ipukr.cn
 *
 * @param
 * @return
 *
 *************************************************************** */
@Data
public class PaginationResponseWarpper<T> {
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
    private List<T> data;

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
     * @param code
     * @param msg
     * @param data
     */
    public PaginationResponseWarpper(int code, String msg , List<T> data) {
        this.code = code;
        this.success = code == 200;
        this.msg = msg;
        this.data = data;
        if(data instanceof PageList) {
            Paginator paginator = ((PageList) data).getPaginator();
            this.count = paginator.getTotalCount();
            this.pageIndex = paginator.getPage();
            this.pageSize = paginator.getLimit();
            this.pageCount = paginator.getTotalPages();
        }
    }


    /**
     * @param status
     * @return
     */
    public static PaginationResponseWarpperBuilder status(HttpStatus status) {
        return new PaginationResponseWarpperBuilder(status);
    }


    /**
     * @return
     */
    public static PaginationResponseWarpperBuilder ok() {
        return new PaginationResponseWarpperBuilder(HttpStatus.OK);
    }

    /**
     *
     */
    public static class PaginationResponseWarpperBuilder {

        /**
         * 提示信息
         */
        private String msg = "请求成功";
        /**
         * 提示信息
         */
        private Integer code;
        /**
         * 数据
         * */
        private List data;


        public PaginationResponseWarpperBuilder(HttpStatus status) {
            this.code = status.value();
        }

        @Deprecated
        public PaginationResponseWarpperBuilder header(String headerName, String... headerValues) {
            return this;
        }


        public PaginationResponseWarpperBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }


        public PaginationResponseWarpperBuilder code(Integer code) {
            this.code = code;
            return this;
        }

        public PaginationResponseWarpperBuilder data(List data) {
            this.data = data;
            return this;
        }

        public PaginationResponseWarpper build() {
            return new PaginationResponseWarpper(code, msg, data);
        }

        public PaginationResponseWarpper body(List data) {
            this.data = data;
            return new PaginationResponseWarpper(code, msg, data);
        }
    }

}
