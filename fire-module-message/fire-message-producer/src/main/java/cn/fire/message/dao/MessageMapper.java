package cn.fire.message.dao;

import cn.fire.message.api.pojo.entity.TransactionMessageDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: wangzc
 * @Date: 2020/9/27 10:17
 */
@Mapper
public interface MessageMapper extends BaseMapper<TransactionMessageDO> {
}
