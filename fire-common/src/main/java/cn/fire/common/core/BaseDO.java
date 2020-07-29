package cn.fire.common.core;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: wangzhichao
 * @Date: 2020/7/29 14:06
 */

@Data
public class BaseDO implements Serializable {

    private Long id;

    @Version
    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    @TableLogic
    private Integer deleted;

}
