package cn.fire.common.web.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: wangzc
 * @Date: 2020/8/4 11:04
 */

@Data
@ApiModel(description = "统一请求体Wrapper")
public class Request<T> {

    @ApiModelProperty("数据签名")
    private String sign;

    @ApiModelProperty("时间戳")
    private String timestamp;

    @ApiModelProperty("请求数据")
    private T data;

}
