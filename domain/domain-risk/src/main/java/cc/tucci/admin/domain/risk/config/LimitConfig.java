package cc.tucci.admin.domain.risk.config;

import cc.tucci.admin.domain.risk.service.LimitService;
import cc.tucci.admin.domain.risk.service.RedissonLimitService;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tucci
 */
@Configuration
@EnableConfigurationProperties({LimitProperties.class})
public class LimitConfig {

    @Bean
    @ConditionalOnProperty(name = "limit.redisson.enabled", havingValue = "true")
    @ConditionalOnMissingBean(LimitService.class)
    public LimitService redissonLimitService(RedissonClient redissonClient) {
        return new RedissonLimitService(redissonClient);
    }

}
