package cn.fire.user.service;

import cn.fire.user.api.exception.UserException;
import cn.fire.user.api.pojo.entity.UserDO;

/**
 * @Author: wangzc
 * @Date: 2020/7/31 11:17
 */

public interface IUserService {

    /**
     * findById
     * @param userId
     * @return
     */
    UserDO getById(Long userId) throws UserException;


    UserDO getByMobile(String mobile) throws UserException;


    Boolean deleteByUserId(Long userId) throws UserException;

}
