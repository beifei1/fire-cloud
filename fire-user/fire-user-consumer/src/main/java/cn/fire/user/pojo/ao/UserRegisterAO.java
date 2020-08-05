package cn.fire.user.pojo.ao;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author: wangzc
 * @Date: 2020/7/29 16:42
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(description = "用户注册AO")
public class UserRegisterAO {

    @NotBlank(message = "用户昵称不能为空")
    private String nickName;

    @NotBlank(message = "手机号码不能为空")
    private String mobile;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "用户性别不能为空")
    private Integer gender;

}
