package cc.tucci.admin.domain.captcha.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tucci
 */
@ConfigurationProperties("captcha.image")
public class CaptchaImageProperties {

    private Kaptcha kaptcha;

    public static class Kaptcha {
        private boolean enabled;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    public Kaptcha getKaptcha() {
        return kaptcha;
    }

    public void setKaptcha(Kaptcha kaptcha) {
        this.kaptcha = kaptcha;
    }
}
