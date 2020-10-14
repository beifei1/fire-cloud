package cn.fire.message.anno;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

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


}
