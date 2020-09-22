package cn.fire.user.config;

import cn.fire.common.web.core.request.JUser;
import cn.fire.common.web.handler.GlobalUserProfileResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Spring WebMvcConfig
 * @Author: wangzc
 * @Date: 2020/8/26 10:25
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 在header中取出用户信息, 并注入到方法参数中
     * @see cn.fire.user.controller.UserController#detail(JUser)
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new GlobalUserProfileResolver());
    }

}
