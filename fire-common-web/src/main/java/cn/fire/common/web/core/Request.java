package cn.fire.common.web.core;

import lombok.Data;

/**
 * @Author: wangzc
 * @Date: 2020/8/4 11:04
 */

@Data
public class Request<T> {

    private String sign;
    
    private T data;

}
