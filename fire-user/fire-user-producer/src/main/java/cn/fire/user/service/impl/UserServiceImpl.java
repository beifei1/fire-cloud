package cn.fire.user.service.impl;

import cn.fire.user.api.pojo.entity.UserDO;
import cn.fire.user.dao.UserMapper;
import cn.fire.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author: wangzc
 * @Date: 2020/7/31 11:17
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,UserDO> implements IUserService {
    @Override
    public UserDO getById(Long userId) {
        return super.getById(userId);
    }
}
