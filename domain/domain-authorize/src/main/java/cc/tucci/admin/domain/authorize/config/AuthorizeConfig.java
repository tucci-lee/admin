package cc.tucci.admin.domain.authorize.config;

import cc.tucci.admin.domain.authorize.authc.Authenticator;
import cc.tucci.admin.domain.authorize.authc.DefaultAuthenticator;
import cc.tucci.admin.domain.authorize.token.JwtTokenGenerator;
import cc.tucci.admin.domain.authorize.token.TokenGenerator;
import cc.tucci.admin.domain.authorize.token.UuidTokenGenerator;
import cc.tucci.admin.domain.authorize.token.repository.RedissonTokenRepository;
import cc.tucci.admin.domain.authorize.token.repository.RedisTemplateTokenRepository;
import cc.tucci.admin.domain.authorize.token.repository.TokenRepository;
import org.redisson.api.RedissonClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author tucci
 */
@Configuration
@EnableConfigurationProperties(AuthorizeProperties.class)
public class AuthorizeConfig {

    private final AuthorizeProperties authorizeProperties;

    public AuthorizeConfig(AuthorizeProperties authorizeProperties) {
        this.authorizeProperties = authorizeProperties;
    }

    @Bean
    public Authenticator authenticator(RedisTemplate redisTemplate,
                                       RedissonClient redissonClient) {
        AuthorizeProperties.Token token = authorizeProperties.getToken();

        TokenGenerator tokenGenerator;
        switch (token.getGenerator()) {
            case JWT:
                tokenGenerator = new JwtTokenGenerator(token.getJwt().getSecret());
                break;
            default:
                tokenGenerator = new UuidTokenGenerator();
                break;
        }

        TokenRepository tokenRepository;
        switch (token.getRepository()) {
            case redisTemplate:
                tokenRepository = new RedisTemplateTokenRepository(redisTemplate);
                break;
            default:
                tokenRepository = new RedissonTokenRepository(redissonClient);
                break;
        }

        DefaultAuthenticator authorizeRepository = new DefaultAuthenticator(tokenGenerator, tokenRepository);
        authorizeRepository.setKeyPrefix(authorizeProperties.getKeyPrefix());
        authorizeRepository.setExpireTime(authorizeProperties.getExpireTime());
        authorizeRepository.setRefreshTime(authorizeProperties.getRefreshTime());
        authorizeRepository.setOnlineCount(authorizeProperties.getOnlineCount());
        authorizeRepository.setKickedOut(authorizeProperties.isKickedOut());
        return authorizeRepository;
    }
}
