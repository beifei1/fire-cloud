package cn.fire.common.web.anno;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * 处理Controller Response使用R自动包裹响应对象
 * @see cn.fire.common.web.handler.GlobalResultBodyHandler
 * @Author: wangzc
 * @Date: 2020/9/8 9:29
 */

@Documented
@ResponseBody
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Body {

}
