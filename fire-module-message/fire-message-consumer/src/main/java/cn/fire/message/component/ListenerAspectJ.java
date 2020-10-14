package cn.fire.message.component;

import cn.fire.common.web.util.RedisUtil;
import cn.fire.message.anno.QueueListener;
import cn.fire.message.api.pojo.dto.SerializableMessage;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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

    @SneakyThrows
    @Around("@annotation(cn.fire.message.anno.QueueListener)")
    public Object loop(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        QueueListener annotation = method.getAnnotation(QueueListener.class);
        String queueName = annotation.queue();

        //获取方法参数值
        Object[] paramValues = joinPoint.getArgs();

        String message = (String) redisUtil.rPop(queueName);

        log.info("=========================================:{}", message);

        if (StringUtils.isNotBlank(message)) {
            SerializableMessage serializableMessage = JSONObject.parseObject(message, SerializableMessage.class);

            String messageId = serializableMessage.getMessageId();
            String messageData = (String) serializableMessage.getData();

            //为参数赋值
            paramValues[0] = messageId;
            paramValues[1] = messageData;

            return joinPoint.proceed(paramValues);
        }
        return false;
    }

}
