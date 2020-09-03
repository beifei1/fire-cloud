package cn.fire.user.service.impl;

import cn.fire.common.web.redis.RedissonUtil;
import cn.fire.user.api.exception.UserException;
import cn.fire.user.api.pojo.entity.RoleDO;
import cn.fire.user.api.pojo.entity.UserDO;
import cn.fire.user.api.pojo.entity.UserRoleDO;
import cn.fire.user.dao.RoleMapper;
import cn.fire.user.dao.UserMapper;
import cn.fire.user.dao.UserRoleMapper;
import cn.fire.user.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: wangzc
 * @Date: 2020/7/31 11:17
 */

@Service
@CacheConfig(cacheNames = "UserCache:")
public class UserServiceImpl extends ServiceImpl<UserMapper,UserDO> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RedissonUtil redissonUtil;

    @Override
    @Cacheable(key = "#userId")
    public UserDO getById(Long userId) throws UserException {
        return super.getById(userId);
    }

    @Override
    @Cacheable(key = "#mobile")
    public UserDO getByMobile(String mobile) throws UserException {
        return userMapper.selectOne(new QueryWrapper<UserDO>().eq(StringUtils.isNoneBlank(mobile),"mobile",mobile));
    }

    @Override
    @CacheEvict(cacheNames = "user:", key = "#userId")
    public Boolean deleteByUserId(Long userId) throws UserException {
        UserDO user = userMapper.selectById(userId);
        if (Objects.isNull(user)) {
            throw UserException.instance(UserException.ErrorEnum.USER_NOT_EXIST);
        }
        return userMapper.deleteById(userId) > 0;

    }

    @Override
    public UserDO getByMobileAndCode(String mobile, String smsCode) throws UserException {
        return userMapper.selectOne(new QueryWrapper<UserDO>().eq("mobile",mobile));
    }

    @Override
    public UserDO getByMobileAndPassword(String mobile, String password) throws UserException {
        return userMapper.selectOne(new QueryWrapper<UserDO>().eq("mobile",mobile) .eq("password",password));
    }

    @Override
    @Cacheable(cacheNames = "user:role" ,key = "#userId")
    public List<RoleDO> getByRoleUserId(Long userId) {

        List<UserRoleDO> userRoles = userRoleMapper.selectList(new QueryWrapper<UserRoleDO>().eq("user_id",userId));

        if(CollectionUtils.isNotEmpty(userRoles)) {

            List<Long> roleIds = userRoles.stream().map(UserRoleDO::getRoleId).collect(Collectors.toList());

            return roleMapper.selectList(new QueryWrapper<RoleDO>().in("id",roleIds));
        }

        return Lists.newArrayListWithCapacity(0);
    }


}
