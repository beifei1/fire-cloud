package cn.fire.user.pojo.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: wangzc
 * @Date: 2020/8/3 15:00
 */

@Data
@AllArgsConstructor
@ApiModel("用户登录请求体")
public class UserLoginAO {

    @ApiModelProperty(value = "电话号码",required = true)
    @NotBlank(message = "电话号码不能为空")
    private String mobile;

    @ApiModelProperty(value = "密码",required = true)
    @NotBlank(message = "密码不能为空")
    private String password;

}
