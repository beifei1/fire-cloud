package cn.fire.message.service.impl;

import cn.fire.common.web.util.RedisUtil;
import cn.fire.message.api.exception.MessageException;
import cn.fire.message.api.pojo.entity.TransactionMessageDO;
import cn.fire.message.dao.MessageMapper;
import cn.fire.message.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wangzc
 * @Date: 2020/9/27 9:42
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, TransactionMessageDO> implements IMessageService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MessageMapper messageMapper;

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
