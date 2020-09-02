package cn.fire.user.api.pojo.entity;

import cn.fire.common.core.BaseDO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.List;

/**
 * @Author: wangzc
 * @Date: 2020/8/14 10:57
 */

@Data
@TableName("t_role")
public class RoleDO extends BaseDO {

    private String roleName;

    private String roleDesc;

}
