package cn.fire.user.pojo.vo;

import lombok.Data;

/**
 * @Author: wangzc
 * @Date: 2020/8/3 14:59
 */

@Data
public class UserLoginVO {

    private String accessToken;

    private String refreshToken;

    private Integer expire;

}
