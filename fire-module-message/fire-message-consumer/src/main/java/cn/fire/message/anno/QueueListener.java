package cn.fire.message.anno;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wangzc
 * @Date: 2020/9/28 9:39
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface QueueListener {

    /**
     * 监听的队列名称
     * @return
     */
    @AliasFor("queue")
    String value() default "";

    /**
     * 监听的队列名称
     * @return
     */
    @AliasFor("value")
    String queue() default "";

    /**
     * 到队列获取数据的间隔时间
     * @return
     */
    int interval() default 3;

    /**
     * 时间单位
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;


}
