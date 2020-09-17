package cn.fire.common.web.lock.impl;

import cn.fire.common.web.lock.DistributedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @Author: wangzc
 * @Date: 2020/9/17 11:11
 */
@Component
@ConditionalOnBean(RedissonClient.class)
public class RedisDistributeLock implements DistributedLock {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public <T> T lock(String key, Supplier<T> success, Supplier<T> failure) {
//        RLock lock = redissonClient.getLock(key);
//        lock.lock();
        return success.get();
    }

    @Override
    public <T> T lock(String key, TimeUnit unit, int leaseTime, Supplier<T> success, Supplier<T> failure) {
        return null;
    }

    @Override
    public <T> T tryLock(String key, TimeUnit timeUnit, int waitTime, Supplier<T> success, Supplier<T> failure) {
        return null;
    }

    @Override
    public <T> T tryLock(String key, TimeUnit timeUnit, int waitTime, int leaseTime, Supplier<T> success, Supplier<T> failure) {
        return null;
    }

    @Override
    public <T> T unLock(String key) {
        return null;
    }
}
