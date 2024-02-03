package cc.tucci.admin.domain.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tucci
 */
@ConfigurationProperties(prefix = "cache")
public class CacheProperties {

    private Redisson redisson;
    private RedisTemplate redisTemplate;

    public static class Redisson {
        private boolean enabled;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    public static class RedisTemplate {
        private boolean enabled;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    public Redisson getRedisson() {
        return redisson;
    }

    public void setRedisson(Redisson redisson) {
        this.redisson = redisson;
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
