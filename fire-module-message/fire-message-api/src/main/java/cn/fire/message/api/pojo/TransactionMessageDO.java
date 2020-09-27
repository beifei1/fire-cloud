package cn.fire.message.api.pojo;

import cn.fire.common.core.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: wangzc
 * @Date: 2020/9/22 10:38
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionMessageDO extends BaseDO {

    /**
     * 消息编辑人
     */
    private String editor;

    /**
     * 消息创建人
     */
    private String creator;

    /**
     * 消息Id
     */
    private String messageId;

    /**
     * 消息内容
     */
    private String messageBody;

    /**
     * 消息队列名称
     */
    private String queueName;

    /**
     * 消息发送次数
     */
    private int sendTimes;

    /**
     * 消息是否为死亡消息
     */
    private String alreadyDead;

    /**
     * 消息状态
     */
    private String status;

}
