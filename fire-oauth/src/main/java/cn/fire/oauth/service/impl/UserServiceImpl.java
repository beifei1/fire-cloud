package cn.fire.oauth.service.impl;

import cn.fire.oauth.feign.UserFeign;
import cn.fire.oauth.pojo.dto.UserDTO;
import cn.fire.oauth.service.IUserService;
import cn.fire.user.api.exception.UserException;
import cn.fire.user.api.pojo.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @Author: wangzc
 * @Date: 2020/8/7 10:50
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserFeign userFeign;

    @Override
    public UserDTO getByUserMobile(String mobile) throws UserException {
        UserDO userDO = userFeign.getByMobile(mobile);
        return null;
    }

    @Override
    public UserDTO getByMobileAndCode(String mobile, String smsCode) throws UserException {
        return null;
    }

    @Override
    public UserDTO getByMobileAndPassword(String mobile, String password) throws UserException {
        return UserDTO.builder().userName("wang").authorities(new ArrayList<>()).build();
    }

}
