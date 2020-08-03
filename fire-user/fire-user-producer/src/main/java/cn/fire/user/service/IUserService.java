package cn.fire.user.service;

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
    UserDO getById(Long userId);


    UserDO getByMobile(String mobile);

}
