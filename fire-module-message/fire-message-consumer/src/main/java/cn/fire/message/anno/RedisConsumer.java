package cn.fire.message.anno;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author: wangzc
 * @Date: 2020/9/28 9:47
 */

@Component
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface RedisConsumer {
}
