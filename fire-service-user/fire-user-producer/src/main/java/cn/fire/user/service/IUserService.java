package cn.fire.user.service;

import cn.fire.user.api.exception.UserException;
import cn.fire.user.api.pojo.entity.RoleDO;
import cn.fire.user.api.pojo.entity.UserDO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: wangzc
 * @Date: 2020/7/31 11:17
 */

public interface IUserService {

    UserDO getById(Long userId) throws UserException;

    UserDO getByMobile(String mobile) throws UserException;

    Boolean deleteByUserId(Long userId) throws UserException;

    UserDO getByMobileAndCode(String mobile,String smsCode) throws UserException;

    UserDO getByMobileAndPassword(String mobile,String password) throws UserException;

    List<RoleDO> getByRoleUserId(Long userId);

}
