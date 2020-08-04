package cn.fire.common.web.core;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: wangzhichao
 * @Date: 2020/7/29 15:00
 */

@ToString
@Schema(title = "统一响应体")
public class R<T> implements Serializable {

    @Schema(description = "成功消息体",name ="OK")
    private static final String OK = "ok";
    @Schema(description = "失败消息体",name = "ERROR")
    private static final String ERROR = "deny";

    @Getter
    @Setter
    @Schema(description = "响应头")
    private Meta meta;
    @Setter
    @Getter
    @Schema(description = "响应体")
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
        @Schema(title = "本次请求是否成功",name = "success")
        private boolean success;
        @Getter
        @Schema(title = "响应消息",name = "msg")
        private String msg;
        @Getter
        @Schema(title = "业务码",name = "msg")
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
