package cn.fire.user.api.pojo.entity;

import cn.fire.common.core.BaseDO;
import cn.fire.common.enums.GenderEnum;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @Author: wangzhichao
 * @Date: 2020/7/29 14:47
 */
@Data
@ToString
@TableName("t_user")
public class UserDO extends BaseDO {

    private String userName;

    private String mobile;

    private GenderEnum gender;

    private String password;

    private String avatar;

    private LocalDateTime birthDate;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    private Integer locked;

}
