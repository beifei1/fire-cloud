package cn.fire.user.controller;

import cn.fire.user.api.client.UserFeignClient;
import cn.fire.user.api.exception.UserException;
import cn.fire.user.api.pojo.entity.RoleDO;
import cn.fire.user.api.pojo.entity.UserDO;
import cn.fire.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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

    @Override
    public UserDO getByMobile(String mobile) {
        return userService.getByMobile(mobile);
    }

    @Override
    public Boolean deleteByUserId(Long userId) {
        return userService.deleteByUserId(userId);
    }

    @Override
    public UserDO getByMobileAndCode(String mobile, String smsCode) throws UserException {
        return userService.getByMobileAndCode(mobile,smsCode);
    }

    @Override
    public UserDO getByMobileAndPassword(String mobile, String password) throws UserException {
        return userService.getByMobileAndPassword(mobile,password);
    }

    @Override
    public List<RoleDO> getRoleByUserId(Long userId) {
        return userService.getByRoleUserId(userId);
    }

}
