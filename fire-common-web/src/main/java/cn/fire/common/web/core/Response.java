package cn.fire.common.web.core;

import lombok.Getter;

import java.io.Serializable;

/**
 * @Author: wangzhichao
 * @Date: 2020/7/29 15:00
 */

public class Response<T> implements Serializable {

    private static final String OK = "ok";
    private static final String ERROR = "error";

    @Getter
    private Meta meta;
    @Getter
    private T t;

    public Response() {

    }

    public Response ok() {
        this.meta = new Meta(true,OK);
        return this;
    }

    public Response ok(T t) {
        this.meta = new Meta(true,OK);
        this.t = t;
        return this;
    }

    public Response deny() {
        this.meta = new Meta(false,ERROR);
        return this;
    }

    public Response deny(String message) {
        this.meta = new Meta(false,message);
        return this;
    }

    public Response deny(Integer code,String message) {
        this.meta = new Meta(false,code,message);
        return this;
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
