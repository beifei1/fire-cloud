package cn.fire.common.web.core.request;

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

    @ApiModelProperty("请求头信息,body参数 + nonce,timestamp,secret字典序排序计算MD5得到sign")
    private RequestMeta meta;

    @ApiModelProperty("请求数据")
    private T body;

}
