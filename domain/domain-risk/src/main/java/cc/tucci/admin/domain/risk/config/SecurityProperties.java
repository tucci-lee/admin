package cc.tucci.admin.domain.risk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tucci
 */
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    private RSASecurityProperties rsa;

    public static class RSASecurityProperties {
        private boolean enabled;
        private String publicKey;
        private String privateKey;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public void setPublicKey(String publicKey) {
            this.publicKey = publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public void setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
        }
    }

    public RSASecurityProperties getRsa() {
        return rsa;
    }

    public void setRsa(RSASecurityProperties rsa) {
        this.rsa = rsa;
    }
}
