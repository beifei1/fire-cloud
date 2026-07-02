package cn.fire.common.web.lock.impl;

import cn.fire.common.web.lock.DistributedLock;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Zookeeper 分布式锁实现
 * 基于 Apache Curator 的 InterProcessMutex 实现
 * 用Runnable和 Supplier来传递执行成功或者失败的逻辑
 * @Author: wangzc
 * @Date: 2020/9/17 11:20
 */
@Component
@ConditionalOnClass(name = "org.apache.curator.framework.CuratorFramework")
public class ZKDistributedLock implements DistributedLock {

    private static final Logger log = LoggerFactory.getLogger(ZKDistributedLock.class);

    private static final String LOCK_ROOT_PATH = "/locks/";

    @Autowired
    private CuratorFramework curatorFramework;

    /**
     * 构建ZK锁路径
     */
    private String buildLockPath(String key) {
        return key.startsWith("/") ? key : LOCK_ROOT_PATH + key;
    }

    /**
     * 释放锁（内部使用，传入已获取的mutex实例）
     */
    private void releaseMutex(InterProcessMutex mutex) {
        try {
            if (mutex != null && mutex.isAcquiredInThisProcess()) {
                mutex.release();
            }
        } catch (Exception e) {
            log.warn("Failed to release ZK lock mutex", e);
        }
    }

    @Override
    public void lock(String key, Runnable success, Runnable failure) {
        String lockPath = buildLockPath(key);
        InterProcessMutex mutex = new InterProcessMutex(curatorFramework, lockPath);
        try {
            mutex.acquire();
            success.run();
        } catch (Exception e) {
            log.error("ZK lock acquire failed, key: {}", key, e);
            failure.run();
        } finally {
            releaseMutex(mutex);
        }
    }

    @Override
    public void lock(String key, TimeUnit timeUnit, int leaseTime, Runnable success, Runnable failure) {
        // ZK 临时节点自带会话超时机制，leaseTime 通过 Curator 的 acquire(time, unit) 间接控制
        // 此处 leaseTime 作为锁获取超时时间使用
        String lockPath = buildLockPath(key);
        InterProcessMutex mutex = new InterProcessMutex(curatorFramework, lockPath);
        try {
            if (mutex.acquire(leaseTime, timeUnit)) {
                success.run();
            } else {
                failure.run();
            }
        } catch (Exception e) {
            log.error("ZK lock acquire failed, key: {}", key, e);
            failure.run();
        } finally {
            releaseMutex(mutex);
        }
    }

    @Override
    public void tryLock(String key, Runnable success, Runnable failure) {
        String lockPath = buildLockPath(key);
        InterProcessMutex mutex = new InterProcessMutex(curatorFramework, lockPath);
        try {
            if (mutex.acquire(0, TimeUnit.SECONDS)) {
                success.run();
            } else {
                failure.run();
            }
        } catch (Exception e) {
            log.error("ZK tryLock failed, key: {}", key, e);
            failure.run();
        } finally {
            releaseMutex(mutex);
        }
    }

    @Override
    public void tryLock(String key, TimeUnit timeUnit, int waitTime, int leaseTime, Runnable success, Runnable failure) {
        // ZK锁通过临时节点实现，leaseTime 由ZK会话超时控制
        // 此处使用 waitTime 作为锁等待超时
        String lockPath = buildLockPath(key);
        InterProcessMutex mutex = new InterProcessMutex(curatorFramework, lockPath);
        try {
            if (mutex.acquire(waitTime, timeUnit)) {
                success.run();
            } else {
                failure.run();
            }
        } catch (Exception e) {
            log.error("ZK tryLock failed, key: {}", key, e);
            failure.run();
        } finally {
            releaseMutex(mutex);
        }
    }

    @Override
    public void tryLock(String key, TimeUnit timeUnit, int waitTime, Runnable success, Runnable failure) {
        String lockPath = buildLockPath(key);
        InterProcessMutex mutex = new InterProcessMutex(curatorFramework, lockPath);
        try {
            if (mutex.acquire(waitTime, timeUnit)) {
                success.run();
            } else {
                failure.run();
            }
        } catch (Exception e) {
            log.error("ZK tryLock failed, key: {}", key, e);
            failure.run();
        } finally {
            releaseMutex(mutex);
        }
    }

    @Override
    public <T> T lock(String key, Supplier<T> success, Supplier<T> failure) {
        String lockPath = buildLockPath(key);
        InterProcessMutex mutex = new InterProcessMutex(curatorFramework, lockPath);
        try {
            mutex.acquire();
            return success.get();
        } catch (Exception e) {
            log.error("ZK lock acquire failed, key: {}", key, e);
            return failure.get();
        } finally {
            releaseMutex(mutex);
        }
    }

    @Override
    public <T> T lock(String key, TimeUnit timeUnit, int leaseTime, Supplier<T> success, Supplier<T> failure) {
        String lockPath = buildLockPath(key);
        InterProcessMutex mutex = new InterProcessMutex(curatorFramework, lockPath);
        try {
            if (mutex.acquire(leaseTime, timeUnit)) {
                return success.get();
            } else {
                return failure.get();
            }
        } catch (Exception e) {
            log.error("ZK lock acquire failed, key: {}", key, e);
            return failure.get();
        } finally {
            releaseMutex(mutex);
        }
    }

    @Override
    public <T> T tryLock(String key, Supplier<T> success, Supplier<T> failure) {
        String lockPath = buildLockPath(key);
        InterProcessMutex mutex = new InterProcessMutex(curatorFramework, lockPath);
        try {
            if (mutex.acquire(0, TimeUnit.SECONDS)) {
                return success.get();
            } else {
                return failure.get();
            }
        } catch (Exception e) {
            log.error("ZK tryLock failed, key: {}", key, e);
            return failure.get();
        } finally {
            releaseMutex(mutex);
        }
    }

    @Override
    public <T> T tryLock(String key, TimeUnit timeUnit, int waitTime, int leaseTime, Supplier<T> success, Supplier<T> failure) {
        String lockPath = buildLockPath(key);
        InterProcessMutex mutex = new InterProcessMutex(curatorFramework, lockPath);
        try {
            if (mutex.acquire(waitTime, timeUnit)) {
                return success.get();
            } else {
                return failure.get();
            }
        } catch (Exception e) {
            log.error("ZK tryLock failed, key: {}", key, e);
            return failure.get();
        } finally {
            releaseMutex(mutex);
        }
    }

    @Override
    public <T> T tryLock(String key, TimeUnit timeUnit, int waitTime, Supplier<T> success, Supplier<T> failure) {
        String lockPath = buildLockPath(key);
        InterProcessMutex mutex = new InterProcessMutex(curatorFramework, lockPath);
        try {
            if (mutex.acquire(waitTime, timeUnit)) {
                return success.get();
            } else {
                return failure.get();
            }
        } catch (Exception e) {
            log.error("ZK tryLock failed, key: {}", key, e);
            return failure.get();
        } finally {
            releaseMutex(mutex);
        }
    }

    @Override
    public void unLock(String key) {
        try {
            String lockPath = buildLockPath(key);
            // 通过强制删除ZK节点来释放锁，适用于跨线程/跨进程的锁释放场景
            curatorFramework.delete().deletingChildrenIfNeeded().forPath(lockPath);
        } catch (Exception e) {
            // 节点可能已不存在或未创建，忽略
            log.debug("ZK unlock ignored, key: {}, reason: {}", key, e.getMessage());
        }
    }
}
