package cn.fire.oauth.service;

import cn.fire.oauth.pojo.dto.UserDTO;
import cn.fire.user.api.exception.UserException;
import cn.fire.user.api.pojo.entity.UserDO;

/**
 * @Author: wangzc
 * @Date: 2020/8/7 10:49
 */

public interface IUserService {

    UserDTO getByMobileAndCode(String mobile,String smsCode) throws UserException;

}
