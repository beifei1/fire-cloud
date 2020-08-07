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

import java.util.Objects;

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

    @Override
    public Boolean deleteByUserId(Long userId) throws UserException {
        UserDO user = userMapper.selectById(userId);
        if (Objects.isNull(user)) {
            throw UserException.instance(UserException.ErrorEnum.USER_NOT_EXIST);
        }
        return userMapper.deleteById(userId) > 0;

    }


}
