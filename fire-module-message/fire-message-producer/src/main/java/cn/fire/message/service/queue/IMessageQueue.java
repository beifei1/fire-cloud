package cn.fire.message.service.queue;

import java.io.Serializable;

/**
 * @Author: wangzc
 * @Date: 2020/9/28 9:33
 */

public interface IMessageQueue {


    /**
     * 发送消息
     * @param queueName
     * @param message
     */
    void send(String queueName, Serializable message);

}
