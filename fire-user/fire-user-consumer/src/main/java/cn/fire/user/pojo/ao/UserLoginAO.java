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
@Schema(name = "UserLoginAO:name",title = "UserLoginAO:title")
public class UserLoginAO {

    @Schema(name = "电话号码",title = "电话号码")
    @NotBlank(message = "电话号码不能为空")
    private String mobile;

    @Schema(name = "登录密码", title = "登录密码")
    @NotBlank(message = "密码不能为空")
    private String password;

}
