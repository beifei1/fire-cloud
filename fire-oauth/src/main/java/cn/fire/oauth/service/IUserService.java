package cn.fire.oauth.service;

import cn.fire.oauth.pojo.dto.UserDTO;
import cn.fire.user.api.exception.UserException;
import cn.fire.user.api.pojo.entity.UserDO;

/**
 * @Author: wangzc
 * @Date: 2020/8/7 10:49
 */

public interface IUserService {

        /**
     * 根据手机号查找用户
     * @param mobile
     * @return
     * @throws UserException
     */
    UserDTO getByUserMobile(String mobile) throws UserException;

    /**
     * 根据手机号和短信验证码查询用户
     * @param mobile
     * @param smsCode
     * @return
     * @throws UserException
     */
    UserDTO getByMobileAndCode(String mobile,String smsCode) throws UserException;

    /**
     * 根据手机号和密码查找用户
     * @param mobile
     * @param password
     * @return
     * @throws UserException
     */
    UserDTO getByMobileAndPassword(String mobile,String password) throws UserException;

}
