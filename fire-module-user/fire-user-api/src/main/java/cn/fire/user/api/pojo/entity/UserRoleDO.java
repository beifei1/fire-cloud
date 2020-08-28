package cn.fire.user.api.pojo.entity;

import cn.fire.common.core.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: wangzc
 * @Date: 2020/8/14 10:59
 */

@Data
@TableName("t_user_role_releation")
public class UserRoleDO extends BaseDO {

    private Long userId;

    private Long roleId;

}
