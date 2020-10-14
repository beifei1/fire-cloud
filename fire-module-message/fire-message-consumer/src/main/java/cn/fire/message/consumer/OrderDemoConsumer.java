package cn.fire.message.consumer;

import cn.fire.message.anno.QueueListener;
import cn.fire.message.core.IMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: wangzc
 * @Date: 2020/10/14 11:55
 */

@Slf4j
@Component
public class OrderDemoConsumer implements IMessageHandler {
    @Override
    @QueueListener(value = "ORDER:NOTIFY:QUEUE")
    public void messageHandler(String messageId, String messageBody) {
        log.info(messageId);
        log.info(messageBody);
    }
}
