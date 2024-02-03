package cc.tucci.admin.domain.captcha.config;

import cc.tucci.admin.domain.captcha.service.ImageCaptchaService;
import cc.tucci.admin.domain.captcha.service.KactchaCaptchaService;
import cc.tucci.admin.domain.core.cache.CacheOperate;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author tucci
 */
@Configuration
@EnableConfigurationProperties({CaptchaImageProperties.class})
public class CaptchaImageConfig {

    /**
     * 参数搜索引擎
     *
     * @return Kaptcha
     */
    @Bean
    @ConditionalOnProperty(name = "captcha.image.kaptcha.enabled", havingValue = "true")
    public ImageCaptchaService kactchaCaptchaService(CacheOperate cacheOperate) {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.textproducer.char.string", "1234567890");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
        properties.setProperty("kaptcha.image.width", "130");
        properties.setProperty("kaptcha.image.height", "40");
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        properties.setProperty("kaptcha.textproducer.char.space", "5");
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");

        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);

        return new KactchaCaptchaService(defaultKaptcha, cacheOperate);
    }
}
