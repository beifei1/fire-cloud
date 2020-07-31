package cn.fire.user.controller;

import cn.fire.common.enums.GenderEnum;
import cn.fire.user.api.client.UserFeignClient;
import cn.fire.user.api.pojo.entity.UserDO;
import cn.fire.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @Author: wangzc
 * @Date: 2020/7/31 11:13
 */

@RestController
public class UserController implements UserFeignClient {

    @Autowired
    private IUserService userService;

    @Override
    public UserDO getById(Long userId) {
        return userService.getById(userId);
    }

}
