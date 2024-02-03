package cc.tucci.admin.domain.risk.config;

import cc.tucci.admin.domain.risk.service.RSASecurityService;
import cc.tucci.admin.domain.risk.service.SecurityService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tucci
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig {

    private final SecurityProperties securityProperties;

    public SecurityConfig(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Bean
    @ConditionalOnProperty(name = "security.rsa.enabled", havingValue = "true")
    @ConditionalOnMissingBean(SecurityService.class)
    public SecurityService rsaSecurityService() throws Exception {
        SecurityProperties.RSASecurityProperties rsa = securityProperties.getRsa();
        return new RSASecurityService(rsa.getPublicKey(), rsa.getPrivateKey());
    }

}
