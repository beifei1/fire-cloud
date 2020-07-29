package cn.fire.common.web.core;

import lombok.Getter;

import java.io.Serializable;

/**
 * @Author: wangzhichao
 * @Date: 2020/7/29 15:00
 */

public class R<T> implements Serializable {

    private static final String OK = "ok";
    private static final String ERROR = "deny";

    @Getter
    private Meta meta;
    @Getter
    private T data;

    public R(boolean success, Integer code, String msg) {
        this.meta = new Meta(success,code,msg);
    }

    public R(boolean success,String msg) {
        this.meta = new Meta(success,msg);
    }

    public R() {
        this.meta = new Meta(true,OK);
    }

    public R(T data) {
        this.meta = new Meta(true,OK);
        this.data = data;
    }

    /**
     * Response
     * @return
     */
    public static R ok() {
        return new R();
    }

    public static R ok(String message) {
        return new R(true,message);
    }

    public static <T> R<T> ok(T data) {
        return new R(data);
    }

    public static R fail() {
        return new R(false,ERROR);
    }

    public static R fail(String message) {
        return new R(false,message);
    }

    public static R fail(Integer code, String message) {
        return new R(false,code,message);
    }

    public class Meta {

        @Getter
        private boolean success;
        @Getter
        private String msg;
        @Getter
        private Integer code;

        public Meta(boolean success) {
            this.success = success;
        }

        public Meta(boolean success,String msg) {
            this.success = success;
            this.msg = msg;
        }

        public Meta(boolean success,Integer code,String msg) {
            this.success = success;
            this.code = code;
            this.msg = msg;
        }
    }
}
