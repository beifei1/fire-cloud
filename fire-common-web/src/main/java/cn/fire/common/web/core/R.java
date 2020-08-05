package cn.fire.common.web.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: wangzhichao
 * @Date: 2020/7/29 15:00
 */

@ToString
@ApiModel(description = "统一响应体")
public class R<T> implements Serializable {

    private static final String OK = "ok";
    private static final String ERROR = "deny";

    @Getter
    @Setter
    private Meta meta;
    @Setter
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

    @ApiModel(description = "统一响应体头部信息")
    public class Meta {

        @Getter
        @ApiModelProperty(name = "是否处理成功")
        private boolean success;
        @Getter
        @ApiModelProperty(name = "响应消息")
        private String msg;
        @Getter
        @ApiModelProperty(name = "业务异常码")
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
