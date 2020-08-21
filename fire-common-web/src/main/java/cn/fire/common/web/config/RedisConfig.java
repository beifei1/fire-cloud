package cn.fire.common.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @Author: wangzc
 * @Date: 2020/8/21 9:44
 */

@Configuration
@ConditionalOnProperty(name = "spring.redis.host")
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;


    /**
     * 指定redis template序列化方式
     * @return
     */
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(){
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }


}
