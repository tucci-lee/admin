package cc.tucci.admin.domain.authorize.token.repository;

import cc.tucci.admin.domain.authorize.Authorize;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tucci
 */
public class RedisTemplateTokenRepository implements TokenRepository {

    private final RedisTemplate redisTemplate;

    public RedisTemplateTokenRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void setAuthorize(String key, Authorize value, long expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    @Override
    public void setTokens(String key, List<String> tokens, long expire) {
        redisTemplate.opsForValue().set(key, tokens, expire, TimeUnit.SECONDS);
    }

    @Override
    public Authorize getAuthorize(String key) {
        return (Authorize) redisTemplate.opsForValue().get(key);
    }

    @Override
    public List<String> getTokens(String key) {
        return (List<String>) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void deleteAuthorize(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void deleteTokens(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

}
