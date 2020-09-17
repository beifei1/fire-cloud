package cn.fire.common.web.lock;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @Author: wangzc
 * @Date: 2020/9/17 11:00
 */

public interface DistributedLock {

    /**
     * 加锁 逻辑处理不带返回值，用Runnable传递逻辑
     * @param key
     * @param success 加锁成功逻辑
     * @param failure 加锁失败逻辑
     */
    void lock(String key, Runnable success, Runnable failure);

    /**
     * 加锁 逻辑处理不带返回值，用Runnable传递逻辑
     * @param key
     * @param timeUnit
     * @param leaseTime 锁失效时间
     * @param success 成功逻辑
     * @param failure 失败逻辑
     */
    void lock(String key, TimeUnit timeUnit, int leaseTime, Runnable success, Runnable failure);

    /**
     * 获取锁 逻辑处理不带返回值，用Runnable传递逻辑
     * @param key
     * @param success 成功逻辑
     * @param failure 失败逻辑
     */
    void tryLock(String key, Runnable success, Runnable failure);

    /**
     * 获取锁 不带返回值，用Runnable传递逻辑
     * @param key
     * @param timeUnit
     * @param waitTime 加锁等待时长
     * @param leaseTime 锁失效时间
     * @param success 成功逻辑
     * @param failure 失败逻辑
     */
    void tryLock(String key, TimeUnit timeUnit, int waitTime, int leaseTime, Runnable success, Runnable failure);

    /**
     * 获取锁 不带返回值，用Runnable传递逻辑
     * @param key
     * @param timeUnit
     * @param waitTime 加锁等待时长
     * @param success 成功逻辑
     * @param failure 失败逻辑
     */
    void tryLock(String key, TimeUnit timeUnit, int waitTime, Runnable success, Runnable failure);

    /**
     * 加锁 逻辑处理带返回值
     * @param key
     * @param success
     * @param failure
     * @param <T>
     * @return
     */
    <T> T lock(String key, Supplier<T> success, Supplier<T> failure);

    /**
     * 加锁 逻辑处理带返回值
     * @param key
     * @param timeUnit
     * @param leaseTime 锁失效时长
     * @param success 成功处理逻辑
     * @param failure 失败处理逻辑
     * @param <T>
     * @return
     */
    <T> T lock(String key, TimeUnit timeUnit, int leaseTime, Supplier<T> success, Supplier<T> failure);

    /**
     * 获取锁 逻辑处理带返回值
     * @param key
     * @param success
     * @param failure
     * @param <T>
     * @return
     */
    <T> T tryLock(String key, Supplier<T> success, Supplier<T> failure);

    /**
     * 获取锁 漏记处理带返回值
     * @param key
     * @param timeUnit
     * @param waitTime 加锁等待时长
     * @param leaseTime 锁失效时长
     * @param success 加锁成功逻辑处理
     * @param failure 加锁失败逻辑处理
     * @param <T>
     * @return
     */
    <T> T tryLock(String key, TimeUnit timeUnit, int waitTime, int leaseTime, Supplier<T> success, Supplier<T> failure);

    /**
     * 获取锁 逻辑处理带返回值
     * @param key
     * @param timeUnit
     * @param waitTime 加锁等待时长
     * @param success
     * @param failure
     * @param <T>
     * @return
     */
    <T> T tryLock(String key, TimeUnit timeUnit, int waitTime, Supplier<T> success, Supplier<T> failure);

    /**
     * 释放锁
     * @param key
     */
    void unLock(String key);

}
