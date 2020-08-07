package cn.fire.oauth.feign;

import cn.fire.common.web.config.FeignConfig;
import cn.fire.user.api.client.UserFeignClient;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: wangzc
 * @Date: 2020/8/7 10:52
 */

@FeignClient(name = "${fire.producer-name.user:}",contextId = "userServiceFeign",configuration = FeignConfig.class)
public interface UserFeign extends UserFeignClient {
}
