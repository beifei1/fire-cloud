package cn.fire.user.pojo.ao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: wangzc
 * @Date: 2020/8/3 15:00
 */

@Data
@AllArgsConstructor
@Schema(title = "用户登录请求体")
public class UserLoginAO {

    @Schema(description = "电话号码")
    @NotBlank(message = "电话号码不能为空")
    private String mobile;

    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

}
