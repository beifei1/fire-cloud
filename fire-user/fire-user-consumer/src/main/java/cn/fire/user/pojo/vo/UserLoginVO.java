package cn.fire.user.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: wangzc
 * @Date: 2020/8/3 14:59
 */

@Data
@ApiModel
public class UserLoginVO {

    @ApiModelProperty("访问token")
    private String accessToken;

    @ApiModelProperty("刷新token")
    private String refreshToken;

    @ApiModelProperty("访问token失效时长，单位秒")
    private Integer expire;

}
