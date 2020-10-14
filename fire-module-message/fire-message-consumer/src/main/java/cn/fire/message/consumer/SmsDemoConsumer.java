package cn.fire.message.consumer;

import cn.fire.message.anno.QueueListener;
import cn.fire.message.core.IMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 消费端
 * @Author: wangzc
 * @Date: 2020/9/28 9:46
 */
@Slf4j
@Component
public class SmsDemoConsumer implements IMessageHandler {

    @Override
    @QueueListener(value = "SMS:SEND:QUEUE", interval = 5)
    public void messageHandler(String messageId, String messageBody) {
        log.info("监听到队列消息");
        log.info("消息Id: {}", messageId);
        log.info("消息内容：{}", messageBody);

        //TODO 实现监听逻辑，并调用API进行ack
    }
}
