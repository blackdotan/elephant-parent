package com.blackdotan.elephant.common.web.http;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

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
     * 数据之外，附加内容
     */
    private Map<String, Object> atta;

    public NormalResponseWrapper() {
    }

    public NormalResponseWrapper(int code, Boolean success, String msg, T data, Map<String, Object> atta) {
        this.code = code;
        this.success = success;
        this.msg = msg;
        this.data = data;
        this.atta = atta;
    }

    /**
     * @param code
     * @param msg
     * @param data
     */
    public NormalResponseWrapper(int code, String msg , T data, Map<String, Object> atta) {
        this.code = code;
        this.success = code == 200;
        this.msg = msg;
        this.data = data;
        this.atta = atta;
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
        return status(HttpStatus.OK);
    }

    /**
     * @return
     */
    public static NormalResponseWrapperBuild fail() {
        return status(HttpStatus.NOT_ACCEPTABLE);
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

        /**
         * 数据之外，附加内容
         */
        private Map<String, Object> atta;

        /**
         * @param status
         */
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

        public NormalResponseWrapperBuild atta(Map<String, Object> atta) {
            this.atta = atta;
            return this;
        }

        /**
         * 快速返回
         * @param data
         * @return
         */
        @Deprecated
        public NormalResponseWrapper body(T data) {
            this.data = data;
            return new NormalResponseWrapper(code, msg, data, atta);
        }

        public NormalResponseWrapper build() {
            return new NormalResponseWrapper(code, msg, data, atta);
        }

    }

}
