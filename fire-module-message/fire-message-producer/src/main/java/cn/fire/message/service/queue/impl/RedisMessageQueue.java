package cn.fire.message.service.queue.impl;

import cn.fire.common.web.util.RedisUtil;
import cn.fire.message.service.queue.IMessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author: wangzc
 * @Date: 2020/9/28 9:34
 */

@Component
public class RedisMessageQueue implements IMessageQueue {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void send(String queueName, Serializable message) {
        redisUtil.lPush(queueName, message);
    }
}
