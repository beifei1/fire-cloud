package cn.fire.message.api.pojo;

import cn.fire.common.core.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * @Author: wangzc
 * @Date: 2020/9/22 10:38
 */
@Data
@AllArgsConstructor
public class TransactionMessageDO extends BaseDO {

    	/**
	 * 编辑人
	 */
	private String editor;

	/**
	 * 创建人
	 */
	private String creator;

	/**
	 * 消息ID
	 */
	private String messageId;

	/**
	 * 消息体
	 */
	private String messageBody;

	/**
	 * 队列名称
	 */
	private String queueName;

	/**
	 * 发送次数
	 */
	private int sendTimes;

	/**
	 * 是否已死亡
	 */
	private String alreadyDead;

	/**
	 * 状态״̬
	 */
	private String status;

}
