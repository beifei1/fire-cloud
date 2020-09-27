package cn.fire.message.controller;

import cn.fire.message.api.client.MessageFeignClient;
import cn.fire.message.api.exception.MessageException;
import cn.fire.message.api.pojo.entity.TransactionMessageDO;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangzc
 * @Date: 2020/9/27 9:38
 */
@RestController
public class MessageController implements MessageFeignClient {

    @Override
    public void saveWaitingConfirmMessage(TransactionMessageDO message) throws MessageException {

    }

    @Override
    public void confirmAndSendMessage(String messageId) throws MessageException {

    }

    @Override
    public void saveAndSendMessage(TransactionMessageDO message) throws MessageException {

    }

    @Override
    public void directSendMessage(TransactionMessageDO message) throws MessageException {

    }

    @Override
    public void reSendMessage(TransactionMessageDO message) throws MessageException {

    }

    @Override
    public void updateMessageToDead(String messageId) throws MessageException {

    }

    @Override
    public TransactionMessageDO getMessageByMessageId(String messageId) throws MessageException {
        return null;
    }

    @Override
    public void deleteMessageByMessageId(String messageId) throws MessageException {

    }

    @Override
    public void reSendDeadMessageByQueueName(String queueName, int batchNumber) throws MessageException {

    }

    @Override
    public void sendStringMessage(String queueName, String message) throws MessageException {

    }

}
