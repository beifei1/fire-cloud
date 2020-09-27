package cn.fire.message.service.impl;

import cn.fire.common.exception.BaseException;
import cn.fire.common.web.util.RedisUtil;
import cn.fire.message.api.enums.MessageStatusEnum;
import cn.fire.message.api.enums.MessageSurviveEnum;
import cn.fire.message.api.exception.MessageException;
import cn.fire.message.api.pojo.entity.TransactionMessageDO;
import cn.fire.message.dao.MessageMapper;
import cn.fire.message.service.IMessageService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
        if (Objects.isNull(message)) {
            throw MessageException.instance(BaseException.BaseErrorEnum.INVALID_PARAMTER_ERROR);
        }

        if (StringUtils.isAnyBlank(message.getMessageBody(), message.getMessageId(), message.getQueueName())) {
            throw MessageException.instance(BaseException.BaseErrorEnum.INVALID_PARAMTER_ERROR);
        }

        message.setState(MessageStatusEnum.WAITING_CONFIRM.name());
        message.setAlreadyDead(MessageSurviveEnum.NO.name());
        message.setSendTimes(0);

        save(message);

    }

    @Override
    public void confirmAndSendMessage(String messageId) throws MessageException {
        final TransactionMessageDO message = getMessageByMessageId(messageId);

        if (Objects.isNull(message)) {
            throw MessageException.instance(MessageException.ErrorEnum.MESSAGE_NOT_EXIST);
        }

        message.setState(MessageStatusEnum.SENDING.name());
        updateById(message);

        //TODO redis lpush
    }

    @Override
    public void saveAndSendMessage(TransactionMessageDO message) throws MessageException {

        if (Objects.isNull(message)) {
            throw MessageException.instance(MessageException.ErrorEnum.MESSAGE_NOT_EXIST);
        }

        if (StringUtils.isAnyBlank(message.getMessageBody(), message.getMessageId(), message.getQueueName())) {
            throw MessageException.instance(BaseException.BaseErrorEnum.INVALID_PARAMTER_ERROR);
        }

		message.setState(MessageStatusEnum.SENDING.name());
		message.setAlreadyDead(MessageSurviveEnum.NO.name());
		message.setSendTimes(0);

		save(message);

		//TODO redis lpush

    }

    @Override
    public void directSendMessage(TransactionMessageDO message) throws MessageException {

        if (Objects.isNull(message)) {
            throw MessageException.instance(MessageException.ErrorEnum.MESSAGE_NOT_EXIST);
        }

        if (StringUtils.isAnyBlank(message.getMessageBody(), message.getMessageId(), message.getQueueName())) {
            throw MessageException.instance(BaseException.BaseErrorEnum.INVALID_PARAMTER_ERROR);
        }

        //TODO redis lpush
    }

    @Override
    public void reSendMessage(TransactionMessageDO message) throws MessageException {

        if (Objects.isNull(message)) {
            throw MessageException.instance(MessageException.ErrorEnum.MESSAGE_NOT_EXIST);
        }

        if (StringUtils.isAnyBlank(message.getMessageBody(), message.getMessageId(), message.getQueueName())) {
            throw MessageException.instance(BaseException.BaseErrorEnum.INVALID_PARAMTER_ERROR);
        }

        message.addSendTimes();
        updateById(message);

        //todo redis push
    }

    @Override
    public void updateMessageToDead(String messageId) throws MessageException {
        TransactionMessageDO message = getMessageByMessageId(messageId);
        if (Objects.isNull(message)) {
            throw MessageException.instance(MessageException.ErrorEnum.MESSAGE_NOT_EXIST);
        }

        message.setAlreadyDead(MessageSurviveEnum.YES.name());
        updateById(message);
    }

    @Override
    public TransactionMessageDO getMessageByMessageId(String messageId) throws MessageException {
        QueryWrapper<TransactionMessageDO> wrapper = new QueryWrapper<>();
        wrapper.eq("message_id", messageId);
        return messageMapper.selectOne(wrapper);
    }

    @Override
    public void deleteMessageByMessageId(String messageId) throws MessageException {
        QueryWrapper<TransactionMessageDO> wrapper = new QueryWrapper<>();
        wrapper.eq("message_id", messageId);

        messageMapper.delete(wrapper);
    }

    @Override
    public void reSendDeadMessageByQueueName(String queueName, int batchNumber) throws MessageException {

    }

    @Override
    public void sendStringMessage(String queueName, String message) throws MessageException {

        if (StringUtils.isAnyBlank(queueName, message)) {
            throw MessageException.instance(BaseException.BaseErrorEnum.INVALID_PARAMTER_ERROR);
        }

        //TODO redis lpush
    }
}
