package cn.fire.common.web.core;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: wangzc
 * @Date: 2020/8/4 11:04
 */

@Data
@Schema(title = "请求参数")
public class Request<T> {

    @Schema(title = "数据签名")
    private String sign;

    @Schema(title = "请求时间")
    private String timestamp;

    @Schema(title = "请求数据")
    private T data;

}
