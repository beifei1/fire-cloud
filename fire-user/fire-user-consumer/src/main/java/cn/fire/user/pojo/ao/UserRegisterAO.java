package cn.fire.user.pojo.ao;

import javax.validation.constraints.NotBlank;

/**
 * @Author: wangzc
 * @Date: 2020/7/29 16:42
 */

public class UserRegisterAO {

    @NotBlank(message = "用户昵称不能为空")
    private String nickName;

    @NotBlank(message = "手机号码不能为空")
    private String mobile;

    @NotBlank(message = "密码不能为空")
    private String password;

}
