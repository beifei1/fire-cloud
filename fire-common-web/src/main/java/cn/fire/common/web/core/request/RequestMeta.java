package cn.fire.common.web.core.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @Author: wangzc
 * @Date: 2020/8/21 10:56
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "请求头信息")
public class RequestMeta {

    @Valid
    @NotBlank(message = "数据签名不能为空")
    @ApiModelProperty("数据签名")
    private String sign;

    @Valid
    @NotBlank(message = "请求时间戳不能为空")
    @ApiModelProperty("请求时间戳(到秒)")
    private String timestamp;

    @Valid
    @NotBlank(message = "随机字符串不能为空")
    @ApiModelProperty("随机字符串")
    private String nonce;

}
