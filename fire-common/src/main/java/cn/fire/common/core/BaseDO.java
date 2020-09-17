package cn.fire.common.core;

import com.baomidou.mybatisplus.annotation.TableId;
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

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 乐观锁/更新时间
     */
    @Version
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 逻辑删除标识位
     */
    @TableLogic
    private Integer deleted;

}
