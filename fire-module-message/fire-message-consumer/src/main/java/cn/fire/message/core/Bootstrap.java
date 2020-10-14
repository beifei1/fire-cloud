package cn.fire.message.core;

import cn.fire.message.api.pojo.dto.SerializableMessage;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 轮询去队列里取数据，如果有兴趣，可以尝试自己用redis提供的发布订阅模型，去通知channel订阅方去队列里取数据
 * @Author: wangzc
 * @Date: 2020/10/14 9:39
 */
@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {

        Map<String, IMessageHandler> beanMap = applicationContext.getBeansOfType(IMessageHandler.class);
        beanMap.forEach((k,b) -> {
            log.info("Loading IMessageHandler实现类, beanName:{},instance:{}", k, b);

            ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(
                    1,
                    new BasicThreadFactory.Builder().namingPattern(k + "-redis-message-queue-%d").daemon(true).build()
            );

            executor.scheduleAtFixedRate(() -> b.messageHandler(null,null), 5,2, TimeUnit.SECONDS);

        });
    }
}
