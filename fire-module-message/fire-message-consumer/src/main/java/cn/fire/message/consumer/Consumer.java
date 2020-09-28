package cn.fire.message.consumer;

import cn.fire.message.anno.QueueListener;
import cn.fire.message.anno.RedisConsumer;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 消费端
 * @Author: wangzc
 * @Date: 2020/9/28 9:46
 */
@Slf4j
@RedisConsumer
public class Consumer {


    @QueueListener(queue = "", interval = 1 ,timeUnit = TimeUnit.MINUTES)
    public void listener(String message) {
      log.info(message);
    }

}
