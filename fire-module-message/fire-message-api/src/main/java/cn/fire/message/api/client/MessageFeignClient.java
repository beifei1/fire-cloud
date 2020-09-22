package cn.fire.message.api.client;

import cn.fire.message.api.exception.MessageException;
import cn.fire.message.api.pojo.TransactionMessageDO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: wangzc
 * @Date: 2020/9/22 10:34
 */

@RequestMapping("/message")
public interface MessageFeignClient {

    /**
     * 发送需要确认的消息
     * @param message
     */
    @PostMapping("/saveWaitingConfirmMessage")
    void saveWaitingConfirmMessage(TransactionMessageDO message) throws MessageException;

    /**
     * 直接确认并发送消息
     * @param messageId
     */
    @PostMapping("/confirmAndSendMessage")
    void confirmAndSendMessage(String messageId) throws MessageException;

    /**
     * 保存并发送消息
     * @param message
     */
    @PostMapping("/saveAndSendMessage")
    void saveAndSendMessage(TransactionMessageDO message) throws MessageException;

    /**
     * 直接发送消息
     * @param message
     */
    @PostMapping("/directSendMessage")
    void directSendMessage(TransactionMessageDO message) throws MessageException;

    /**
     * 重新发送消息
     * @param message
     */
    @PostMapping("/repeatSendMessage")
    void repeatSendMessage(TransactionMessageDO message) throws MessageException;

    /**
     * 更新消息状态为死亡消息
     * @param messageId
     */
    @PostMapping("/updateMessageToDead")
    void updateMessageToDead(String messageId) throws MessageException;

    /**
     * 根据消息Id获取消息
     * @param messageId
     * @return
     */
    @PostMapping("/getMessageByMessageId")
    TransactionMessageDO getMessageByMessageId(String messageId) throws MessageException;

    /**
     * 根据消息Id删除消息
     * @param messageId
     */
    @PostMapping("/deleteMessageByMessageId")
    void deleteMessageByMessageId(String messageId) throws MessageException;

    /**
     * 根据队列名重新发送已死亡消息
     * @param queueName
     * @param batchNumber
     */
    @PostMapping("/repeatSendDeadMessageByQueueName")
    void repeatSendDeadMessageByQueueName(String queueName, int batchNumber) throws MessageException;

    /**
     * 发送String消息
     * @param queueName
     * @param message
     */
    @PostMapping("/sendStringMessage")
    void sendStringMessage(String queueName, String message) throws MessageException;

}
