package cn.fire.user.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: wangzc
 * @Date: 2020/8/3 14:59
 */

@Data
@Schema(title = "用户登录响应体")
public class UserLoginVO {

    @Schema(description = "访问Token",name = "accessToken")
    private String accessToken;

    @Schema(description = "刷新Token",name = "refreshToken")
    private String refreshToken;

    @Schema(description = "访问Token失效时间")
    private Integer expire;

}
