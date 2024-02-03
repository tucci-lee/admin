package cc.tucci.admin.domain.core.cache;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author tucci
 */
public class RedisTemplateCacheOperate implements CacheOperate {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisTemplateCacheOperate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public <T> void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public <T> void set(String key, T value, int expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public <T> T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public boolean expire(String key, int expireTime) {
        return redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }
}
