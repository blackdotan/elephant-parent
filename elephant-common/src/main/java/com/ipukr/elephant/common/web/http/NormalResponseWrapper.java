package com.ipukr.elephant.common.web.http;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

/****************************************************************
 * 简单返回封装类
 *
 * <p> 避免继承HttpEntity，以支持Feigin类型自动转换 <br>
 *
 * @author black.an wmw@ipukr.cn
 *
 * @param
 * @return
 *
 *************************************************************** */
@Data
public class NormalResponseWrapper<T> {
    /**
     *
     */
    private int code;
    /**
     * 是否成功
     */
    private Boolean success = true;
    /**
     *
     */
    private String msg;
    /**
     *
     */
    private T data;

    /**
     * @param code
     * @param msg
     * @param data
     */
    public NormalResponseWrapper(int code, String msg , T data) {
        this.code = code;
        this.success = code == 200;
        this.msg = msg;
        this.data = data;
    }

    /**
     * @param status
     * @return
     * */
    public static NormalResponseWrapperBuild status(HttpStatus status) {
        return new NormalResponseWrapperBuild(status);
    }


    /**
     * @return
     */
    public static NormalResponseWrapperBuild ok() {
        return new NormalResponseWrapperBuild(HttpStatus.OK);
    }

    public static class NormalResponseWrapperBuild<T> {
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
        private T data;


        public NormalResponseWrapperBuild(HttpStatus status) {
            this.code = status.value();
        }

        @Deprecated
        public NormalResponseWrapperBuild header(String headerName, String... headerValues) {
            return this;
        }


        public NormalResponseWrapperBuild msg(String msg) {
            this.msg = msg;
            return this;
        }


        public NormalResponseWrapperBuild code(Integer code) {
            this.code = code;
            return this;
        }

        public NormalResponseWrapperBuild data(T data) {
            this.data = data;
            return this;
        }

        public NormalResponseWrapper build() {
            return new NormalResponseWrapper(code, msg, data);
        }

        public NormalResponseWrapper body(T data) {
            this.data = data;
            return new NormalResponseWrapper(code, msg, data);
        }
    }



}
