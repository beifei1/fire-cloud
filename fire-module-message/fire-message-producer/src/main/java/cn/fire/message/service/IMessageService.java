package cn.fire.message.service;

import cn.fire.message.api.exception.MessageException;
import cn.fire.message.api.pojo.entity.TransactionMessageDO;

/**
 * @Author: wangzc
 * @Date: 2020/9/27 9:41
 */

public interface IMessageService {

    /**
     * 保存待发送消息
     * @param message
     * @throws MessageException
     */
    void saveWaitingConfirmMessage(TransactionMessageDO message) throws MessageException;

    /**
     * 直接确认并发送消息
     * @param messageId
     */
    void confirmAndSendMessage(String messageId) throws MessageException;

    /**
     * 保存并发送消息
     * @param message
     */
    void saveAndSendMessage(TransactionMessageDO message) throws MessageException;

    /**
     * 直接发送消息
     * @param message
     */
    void directSendMessage(TransactionMessageDO message) throws MessageException;

    /**
     * 重新发送消息
     * @param message
     */
    void reSendMessage(TransactionMessageDO message) throws MessageException;

    /**
     * 更新消息状态为死亡消息
     * @param messageId
     */
    void updateMessageToDead(String messageId) throws MessageException;

    /**
     * 根据消息Id获取消息
     * @param messageId
     * @return
     */
    TransactionMessageDO getMessageByMessageId(String messageId) throws MessageException;

    /**
     * 根据消息Id删除消息
     * @param messageId
     */
    void deleteMessageByMessageId(String messageId) throws MessageException;

    /**
     * 根据队列名重新发送已死亡消息
     * @param queueName
     * @param batchNumber
     */
    void reSendDeadMessageByQueueName(String queueName, int batchNumber) throws MessageException;

    /**
     * 发送String消息
     * @param queueName
     * @param message
     */
    void sendStringMessage(String queueName, String message) throws MessageException;
}
