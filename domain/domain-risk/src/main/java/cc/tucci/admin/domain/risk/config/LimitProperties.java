package cc.tucci.admin.domain.risk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tucci
 */
@ConfigurationProperties("limit")
public class LimitProperties {

    private Redisson redisson;

    public static class Redisson {
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
}
