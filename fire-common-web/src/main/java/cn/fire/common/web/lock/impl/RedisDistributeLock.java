package cn.fire.common.web.lock.impl;

import cn.fire.common.web.lock.DistributedLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * redis 分布式锁实现
 * @Author: wangzc
 * @Date: 2020/9/17 11:11
 */
@Primary
@Component
@ConditionalOnBean(RedissonClient.class)
public class RedisDistributeLock implements DistributedLock {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public <T> T lock(String key, Supplier<T> success, Supplier<T> failure) {
        try {
            redissonClient.getLock(key).lock();
            return success.get();
        } catch (IllegalStateException e) {
            return failure.get();
        }
    }

    @Override
    public <T> T lock(String key, TimeUnit timeUnit, int leaseTime, Supplier<T> success, Supplier<T> failure) {
        try {
            redissonClient.getLock(key).lock(leaseTime, timeUnit);
            return success.get();
        } catch (IllegalStateException e) {
            return failure.get();
        }
    }

    @Override
    public <T> T tryLock(String key, Supplier<T> success, Supplier<T> failure) {
        Boolean bool = redissonClient.getLock(key).tryLock();
        return bool ? success.get() : failure.get();
    }


    @Override
    public <T> T tryLock(String key, TimeUnit timeUnit, int waitTime, int leaseTime, Supplier<T> success, Supplier<T> failure) {
        try {
            Boolean bool = redissonClient.getLock(key).tryLock(waitTime,leaseTime, timeUnit);
            return bool ? success.get() : failure.get();
        } catch (InterruptedException e) {
            return failure.get();
        }
    }

    @Override
    public <T> T tryLock(String key, TimeUnit timeUnit, int waitTime, Supplier<T> success, Supplier<T> failure) {
        try {
            Boolean bool = redissonClient.getLock(key).tryLock(waitTime, timeUnit);
            return bool ? success.get() : failure.get();
        } catch (InterruptedException e) {
            return failure.get();
        }
    }

    @Override
    public void unLock(String key) {
        redissonClient.getLock(key).unlock();
    }
}
