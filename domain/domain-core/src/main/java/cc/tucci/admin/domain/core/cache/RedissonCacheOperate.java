package cc.tucci.admin.domain.core.cache;


import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author tucci
 */
public class RedissonCacheOperate implements CacheOperate {

    private final RedissonClient redissonClient;

    public RedissonCacheOperate(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }


    @Override
    public <T> void set(String key, T value) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        bucket.set(value);
    }

    @Override
    public <T> void set(String key, T value, int expireTime) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        bucket.set(value, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public <T> T get(String key) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    @Override
    public void delete(String key) {
        redissonClient.getBucket(key).delete();
    }

    @Override
    public long getExpire(String key) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        long l = bucket.remainTimeToLive();
        if (l > 0) {
            return l / 1000;
        } else {
            return l;
        }
    }

    @Override
    public boolean expire(String key, int expireTime) {
        return redissonClient.getBucket(key).expire(expireTime, TimeUnit.SECONDS);
    }
}
