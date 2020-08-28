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
     * 乐观所插件
     */
    @Version
    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    /**
     * 逻辑删除标识位
     */
    @TableLogic
    private Integer deleted;

}
