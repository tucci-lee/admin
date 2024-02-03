package cc.tucci.admin.domain.authorize.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tucci
 */
@ConfigurationProperties("authorize")
public class AuthorizeProperties {
    /**
     * token存储前缀
     */
    private String keyPrefix = "token:user:";
    /**
     * token过期时间/秒
     */
    private long expireTime = 30 * 24 * 60 * 60;
    /**
     * token过期时间小于刷新时间，则生成新的token
     */
    private long refreshTime = 3 * 24 * 60 * 60;
    /**
     * 用户同时登录人数，-1为不限制
     */
    private int onlineCount = -1;
    /**
     * 用户同时登录人数达到最大值，是否踢出最先登录的用户
     */
    private boolean kickedOut = true;

    private Token token = new Token();

    public static class Token {

        private TokenGeneratorType generator = TokenGeneratorType.UUID;

        private TokenRepositoryType repository = TokenRepositoryType.redisson;

        private Jwt jwt = new Jwt();

        public TokenGeneratorType getGenerator() {
            return generator;
        }

        public void setGenerator(TokenGeneratorType generator) {
            this.generator = generator;
        }

        public TokenRepositoryType getRepository() {
            return repository;
        }

        public void setRepository(TokenRepositoryType repository) {
            this.repository = repository;
        }

        public Jwt getJwt() {
            return jwt;
        }

        public void setJwt(Jwt jwt) {
            this.jwt = jwt;
        }
    }

    public static class Jwt {
        /**
         * jwt加解密secret，注意保密，避免他人解析
         */
        private String secret = "secret";

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }
    }

    public enum TokenGeneratorType {
        UUID, JWT,
    }

    public enum TokenRepositoryType {
        redisson, redisTemplate,
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(long refreshTime) {
        this.refreshTime = refreshTime;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

    public boolean isKickedOut() {
        return kickedOut;
    }

    public void setKickedOut(boolean kickedOut) {
        this.kickedOut = kickedOut;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
