package cn.fire.message.consumer;

import cn.fire.message.api.client.MessageFeignClient;
import cn.fire.message.api.exception.MessageException;
import cn.fire.message.api.pojo.entity.TransactionMessageDO;
import cn.fire.message.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangzc
 * @Date: 2020/9/27 9:38
 */
@RestController
public class MessageController implements MessageFeignClient {

    @Autowired
    private IMessageService messageService;

    @Override
    public void saveWaitingConfirmMessage(TransactionMessageDO message) throws MessageException {
        messageService.saveWaitingConfirmMessage(message);
    }

    @Override
    public void confirmAndSendMessage(String messageId) throws MessageException {
        messageService.confirmAndSendMessage(messageId);
    }

    @Override
    public void saveAndSendMessage(TransactionMessageDO message) throws MessageException {
        messageService.saveAndSendMessage(message);
    }

    @Override
    public void directSendMessage(TransactionMessageDO message) throws MessageException {
        messageService.directSendMessage(message);
    }

    @Override
    public void reSendMessage(TransactionMessageDO message) throws MessageException {
        messageService.reSendMessage(message);
    }

    @Override
    public void updateMessageToDead(String messageId) throws MessageException {
        messageService.updateMessageToDead(messageId);
    }

    @Override
    public TransactionMessageDO getMessageByMessageId(String messageId) throws MessageException {
        return messageService.getMessageByMessageId(messageId);
    }

    @Override
    public void deleteMessageByMessageId(String messageId) throws MessageException {
        messageService.deleteMessageByMessageId(messageId);
    }

    @Override
    public void reSendDeadMessageByQueueName(String queueName, int batchNumber) throws MessageException {
        messageService.reSendDeadMessageByQueueName(queueName,batchNumber);
    }

    @Override
    public void sendStringMessage(String queueName, String message) throws MessageException {
        messageService.sendStringMessage(queueName, message);
    }

}
