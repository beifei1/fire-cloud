package cn.fire.user.service.impl;

import cn.fire.user.api.exception.UserException;
import cn.fire.user.api.pojo.entity.UserDO;
import cn.fire.user.dao.UserMapper;
import cn.fire.user.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author: wangzc
 * @Date: 2020/7/31 11:17
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,UserDO> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDO getById(Long userId) throws UserException {
        return super.getById(userId);
    }

    @Override
    public UserDO getByMobile(String mobile) throws UserException {
        return userMapper.selectOne(new QueryWrapper<UserDO>().eq(StringUtils.isNoneBlank(mobile),"mobile",mobile));
    }


}
