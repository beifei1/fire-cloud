package cn.fire.gateway.config;

import lombok.Data;
import java.util.List;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: wangzc
 * @Date: 2020/9/1 13:31
 */

@Data
@Component
@EqualsAndHashCode(callSuper = false)
@ConfigurationProperties(prefix = "spring.security.ignore")
public class IgnoreUriConfig {

    private List<String> urls;

}
