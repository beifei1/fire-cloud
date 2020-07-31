package cn.fire.user.api.pojo.entity;

import cn.fire.common.core.BaseDO;
import cn.fire.common.enums.GenderEnum;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @Author: wangzhichao
 * @Date: 2020/7/29 14:47
 */
@Data
@TableName("t_user")
public class UserDO extends BaseDO {

    private String userName;

    private String mobile;

    private GenderEnum gender;

    private String avatar;

    private LocalDateTime birthDate;

    private LocalDateTime lastLoginTime;

    private LocalDateTime update_time;

    private LocalDateTime createTime;

}
