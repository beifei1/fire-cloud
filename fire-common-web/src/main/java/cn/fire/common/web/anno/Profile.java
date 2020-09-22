package cn.fire.common.web.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数自动注入注解
 * @see cn.fire.common.web.handler.GlobalUserProfileResolver
 * @Author: wangzc
 * @Date: 2020/8/26 9:39
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Profile {
}
