package cn.fire.user.pojo.ao;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: wangzc
 * @Date: 2020/8/3 15:00
 */

@Data
@AllArgsConstructor
public class UserLoginAO {

    @NotBlank(message = "电话号码不能为空")
    private String mobile;

    @NotBlank(message = "密码不能为空")
    private String password;

}
