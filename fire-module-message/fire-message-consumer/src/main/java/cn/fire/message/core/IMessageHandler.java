package cn.fire.message.core;

/**
 * @Author: wangzc
 * @Date: 2020/10/14 9:29
 */
public interface IMessageHandler {

    void messageHandler(String messageId, String messageBody);

}
