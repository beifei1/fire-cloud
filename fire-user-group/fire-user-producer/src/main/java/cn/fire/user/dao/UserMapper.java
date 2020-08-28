package cn.fire.user.dao;

import cn.fire.user.api.pojo.entity.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: wangzc
 * @Date: 2020/7/31 11:15
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
