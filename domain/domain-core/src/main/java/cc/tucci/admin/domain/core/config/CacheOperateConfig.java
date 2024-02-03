package cc.tucci.admin.domain.core.config;

import cc.tucci.admin.domain.core.cache.CacheOperate;
import cc.tucci.admin.domain.core.cache.RedisTemplateCacheOperate;
import cc.tucci.admin.domain.core.cache.RedissonCacheOperate;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author tucci
 */
@Configuration
@EnableConfigurationProperties({CacheProperties.class})
public class CacheOperateConfig {

    @Bean
    @ConditionalOnProperty(name = "cache.redisson.enabled", havingValue = "true")
    @ConditionalOnMissingBean(CacheOperate.class)
    public CacheOperate redissonCacheOperate(RedissonClient redissonClient) {
        return new RedissonCacheOperate(redissonClient);
    }

    @Bean
    @ConditionalOnProperty(name = "cache.redistemplate.enabled", havingValue = "true")
    @ConditionalOnMissingBean(CacheOperate.class)
    public CacheOperate redisTemplateCacheOperate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        //将key的序列化设置成StringRedisSerializer
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setHashKeySerializer(keySerializer);

        redisTemplate.afterPropertiesSet();

        return new RedisTemplateCacheOperate(redisTemplate);
    }

}
