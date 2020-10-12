package cn.fire.message.component;

import cn.fire.common.web.util.RedisUtil;
import cn.fire.message.anno.QueueListener;
import cn.fire.message.api.pojo.dto.SerializableMessage;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO 实现逻辑
 * @Author: wangzc
 * @Date: 2020/9/29 14:13
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class ListenerAspectJ {

    private final RedisUtil redisUtil;

    /**
     * 轮询去队列中拉取消息
     * @param joinPoint
     */
    @SneakyThrows
    @Around("@annotation(cn.fire.message.anno.QueueListener)")
    public void loop(ProceedingJoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        QueueListener annotation = method.getAnnotation(QueueListener.class);

        String queueName = annotation.queue();
        int interval = annotation.interval();
        Class clazz = annotation.clazz();

        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("redis-message-queue-%d").daemon(true).build());

        executor.schedule(() -> {

            String message = (String)redisUtil.rPop(queueName);

            log.info("=========================================:{}", message);
            //TODO 给参数赋值
            SerializableMessage message1 = JSONObject.parseObject(message, SerializableMessage.class);
            String messageId = message1.getMessageId();
//            String object = message1.getData();

        }, interval, TimeUnit.SECONDS);

    }

}
