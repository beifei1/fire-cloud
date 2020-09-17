package cn.fire.common.web.lock.impl;

import cn.fire.common.web.lock.DistributedLock;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * TODO 实现zookeeper版的分布式锁
 * @Author: wangzc
 * @Date: 2020/9/17 11:20
 */
public class ZKDistributedLock implements DistributedLock {

    @Override
    public void lock(String key, Runnable success, Runnable failure) {

    }

    @Override
    public void lock(String key, TimeUnit timeUnit, int leaseTime, Runnable success, Runnable failure) {

    }

    @Override
    public void tryLock(String key, Runnable success, Runnable failure) {

    }

    @Override
    public void tryLock(String key, TimeUnit timeUnit, int waitTime, int leaseTime, Runnable success, Runnable failure) {

    }

    @Override
    public void tryLock(String key, TimeUnit timeUnit, int waitTime, Runnable success, Runnable failure) {

    }

    @Override
    public <T> T lock(String key, Supplier<T> success, Supplier<T> failure) {
        return null;
    }

    @Override
    public <T> T lock(String key, TimeUnit timeUnit, int leaseTime, Supplier<T> success, Supplier<T> failure) {
        return null;
    }

    @Override
    public <T> T tryLock(String key, Supplier<T> success, Supplier<T> failure) {
        return null;
    }

    @Override
    public <T> T tryLock(String key, TimeUnit timeUnit, int waitTime, int leaseTime, Supplier<T> success, Supplier<T> failure) {
        return null;
    }

    @Override
    public <T> T tryLock(String key, TimeUnit timeUnit, int waitTime, Supplier<T> success, Supplier<T> failure) {
        return null;
    }

    @Override
    public void unLock(String key) {

    }
}
