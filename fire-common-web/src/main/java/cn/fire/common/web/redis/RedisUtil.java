package cn.fire.common.web.redis;

import cn.fire.common.web.config.GlobalCacheConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wangzc
 * @Date: 2020/8/21 9:39
 */
@Slf4j
@Component
@ConditionalOnBean(GlobalCacheConfig.class)
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @SneakyThrows
    public boolean expire(String key, long time) {
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
        return true;
    }

    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    //============================String=============================

    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }


    public void set(String key, Serializable value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public boolean set(String key, Serializable value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("{}", key, e);
            return false;
        }
    }

    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }


    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }


    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    //================================Map=================================

    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }


    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error("{}", key, e);
            return false;
        }
    }


    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("{}", key, e);
            return false;
        }
    }


    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            log.error("{}", key, e);
            return false;
        }
    }


    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("{}", key, e);
            return false;
        }
    }


    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }


    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }


    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }


    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    //============================set=============================

    public java.util.Set<Serializable> sGet(Serializable key) {
        try {
            return redisTemplate.opsForSet().members((String) key);
        } catch (Exception e) {
            log.error("{}", key, e);
            return null;
        }
    }

    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            log.error("{}", key, e);
            return false;
        }
    }


    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            log.error("{}", key, e);
            return 0;
        }
    }


    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            log.error("{}", key, e);
            return 0;
        }
    }

    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            log.error("{}", key, e);
            return 0;
        }
    }

    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            log.error("{}", key, e);
            return 0;
        }
    }
    //===============================list=================================

    public java.util.List<Serializable> lGet(Serializable key, long start, long end) {
        try {
            return redisTemplate.opsForList().range((String) key, start, end);
        } catch (Exception e) {
            log.error("{}", key, e);
            return null;
        }
    }


    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            log.error("{}", key, e);
            return 0;
        }
    }


    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            log.error("{}", key, e);
            return null;
        }
    }


    public boolean lSet(String key, Serializable value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            log.error("{}", key, e);
            return false;
        }
    }


    public boolean lSet(String key, Serializable value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("{}", key, e);
            return false;
        }
    }


    public boolean lSet(String key, java.util.List<Serializable> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            log.error("{}", key, e);
            return false;
        }
    }


    public boolean lSet(String key, java.util.List<Serializable> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("{}", key, e);
            return false;
        }
    }


    public boolean lUpdateIndex(String key, long index, Serializable value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error("{}", key, e);
            return false;
        }
    }


    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            log.error("{}", key, e);
            return 0;
        }
    }

//    public <T> RBucket<T> getRBucket(String objectName) {
//        RBucket<T> bucket = redissonClient.getBucket(objectName);
//        return bucket;
//    }
//
//    public <K, V> RMap<K, V> getRMap(String objectName) {
//        RMap<K, V> map = redissonClient.getMap(objectName);
//        return map;
//    }
//
//    public <V> RSortedSet<V> getRSortedSet(String objectName) {
//        RSortedSet<V> sortedSet = redissonClient.getSortedSet(objectName);
//        return sortedSet;
//    }
//
//    public <V> RSet<V> getRSet(String objectName) {
//        RSet<V> rSet = redissonClient.getSet(objectName);
//        return rSet;
//    }
//
//    public <V> RList<V> getRList(String objectName) {
//        RList<V> rList = redissonClient.getList(objectName);
//        return rList;
//    }
//
//    public <V> RQueue<V> getRQueue(String objectName) {
//        RQueue<V> rQueue = redissonClient.getQueue(objectName);
//        return rQueue;
//    }
//
//    public <V> RDeque<V> getRDeque(String objectName) {
//        RDeque<V> rDeque = redissonClient.getDeque(objectName);
//        return rDeque;
//    }
//
//
//    public RLock lock(String lockKey) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.lock();
//        return lock;
//    }
//
//    public RLock lock(String lockKey, int leaseTime) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.lock(leaseTime, TimeUnit.SECONDS);
//        return lock;
//    }
//
//    public RLock lock(String lockKey, TimeUnit unit, int timeout) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.lock(timeout, unit);
//        return lock;
//    }
//
//    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
//        RLock lock = redissonClient.getLock(lockKey);
//        try {
//            return lock.tryLock(waitTime, leaseTime, unit);
//        } catch (InterruptedException e) {
//            return false;
//        }
//    }
//
//    public void unlock(String lockKey) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.unlock();
//    }
//
//    public void unlock(RLock lock) {
//        lock.unlock();
//    }
//
//    public RReadWriteLock getRWLock(String objectName) {
//        RReadWriteLock rwlock = redissonClient.getReadWriteLock(objectName);
//        return rwlock;
//    }
//
//
//    public RAtomicLong getRAtomicLong(String objectName) {
//        RAtomicLong rAtomicLong = redissonClient.getAtomicLong(objectName);
//        return rAtomicLong;
//    }
//
//    public RCountDownLatch getRCountDownLatch(String objectName) {
//        RCountDownLatch rCountDownLatch = redissonClient.getCountDownLatch(objectName);
//        return rCountDownLatch;
//    }
}
