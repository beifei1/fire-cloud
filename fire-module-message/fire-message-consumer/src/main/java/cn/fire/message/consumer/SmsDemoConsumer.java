package cn.fire.message.consumer;

import cn.fire.message.anno.QueueListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 消费端
 * @Author: wangzc
 * @Date: 2020/9/28 9:46
 */
@Slf4j
@Component
public class SmsDemoConsumer {

    /**
     * 队列消息监听器
     * @param messageId
     * @param message
     */
    @QueueListener(queue = "", interval = 1, clazz = String.class)
    public void listener(String messageId, String message) {
        log.info("监听到队列消息");
        log.info("消息Id: {}", messageId);
        log.info("消息内容：{}", message);

        //TODO 实现监听逻辑，并调用API进行ack
    }

}
