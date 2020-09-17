package cn.fire.common.web.lock;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @Author: wangzc
 * @Date: 2020/9/17 11:00
 */

public interface DistributedLock {

    <T> T lock(String key, Supplier<T> success, Supplier<T> failure);

    <T> T lock(String key, TimeUnit timeUnit, int leaseTime, Supplier<T> success, Supplier<T> failure);

    <T> T tryLock(String key, Supplier<T> success, Supplier<T> failure);

    <T> T tryLock(String key, TimeUnit timeUnit, int waitTime, int leaseTime, Supplier<T> success, Supplier<T> failure);

    <T> T tryLock(String key, TimeUnit timeUnit, int waitTime, Supplier<T> success, Supplier<T> failure);

    void unLock(String key);

}
