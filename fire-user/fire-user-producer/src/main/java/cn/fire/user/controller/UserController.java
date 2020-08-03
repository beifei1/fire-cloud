package cn.fire.user.controller;

import cn.fire.user.api.client.UserFeignClient;
import cn.fire.user.api.exception.UserException;
import cn.fire.user.api.pojo.entity.UserDO;
import cn.fire.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


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
        throw UserException.instance(UserException.DATA_NOT_EXISTS,"用户不存在");
//        return userService.getById(userId);
    }

    @Override
    public UserDO getByMobile(String mobile) {
        return userService.getByMobile(mobile);
    }

}
