package cn.fire.message.aspect;

import cn.fire.common.web.util.RedisUtil;
import cn.fire.message.anno.QueueListener;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

/**
 * TODO 实现逻辑
 * @Author: wangzc
 * @Date: 2020/9/29 14:13
 */
@Aspect
@Component
public class MessageQueueAspect {

    private final RedisUtil redisUtil;

    @Autowired
    public MessageQueueAspect(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    /**
     * 轮询去队列中拉取消息
     * @param joinPoint
     */
    @Around("@annotation(cn.fire.message.anno.QueueListener)")
    public void loop(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        QueueListener listener = method.getAnnotation(QueueListener.class);

        String queueName = listener.queue();
        int interval = listener.interval();


//        ScheduledExecutorService
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//            }
//        },5000, interval * 1000);

    }

}
