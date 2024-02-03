package cc.tucci.admin.domain.authorize.token.repository;

import cc.tucci.admin.domain.authorize.Authorize;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tucci
 */
public class RedissonTokenRepository implements TokenRepository {

    private final RedissonClient redissonClient;

    public RedissonTokenRepository(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public void setAuthorize(String key, Authorize authorize, long expire) {
        RBucket<Authorize> bucket = redissonClient.getBucket(key);
        bucket.set(authorize, expire, TimeUnit.SECONDS);
    }

    @Override
    public void setTokens(String key, List<String> tokens, long expire) {
        RBucket<List<String>> bucket = redissonClient.getBucket(key);
        bucket.set(tokens, expire, TimeUnit.SECONDS);
    }

    @Override
    public Authorize getAuthorize(String key) {
        RBucket<Authorize> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    @Override
    public List<String> getTokens(String key) {
        RBucket<List<String>> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    @Override
    public void deleteAuthorize(String key) {
        redissonClient.getBucket(key).delete();
    }

    @Override
    public void deleteTokens(String key) {
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
}
