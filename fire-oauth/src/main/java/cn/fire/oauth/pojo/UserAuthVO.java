package cn.fire.oauth.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

/**
 * @Author: wangzc
 * @Date: 2020/8/7 14:13
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Api(value = "用户认证授权响应体")
public class UserAuthVO {

    @ApiModelProperty(value = "访问Token")
    private String accessToken;

    @ApiModelProperty(value = "刷新token")
    private String refreshToken;

    @ApiModelProperty("Token类型")
    private String tokenType;

    @ApiModelProperty("距Token失效秒数")
    private Integer expireSeconds;

    @ApiModelProperty("Token使用范围")
    private Set<String> scope;

}
