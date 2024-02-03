package cc.tucci.admin.domain.authorize.authc;

import cc.tucci.admin.domain.authorize.token.TokenGenerator;
import cc.tucci.admin.domain.authorize.token.repository.TokenRepository;
import cc.tucci.admin.domain.authorize.Authorize;

/**
 * @author tucci
 */
public abstract class AbstractAuthenticator implements Authenticator {

    private String keyPrefix = "token:user:";
    private long expireTime = 30 * 24 * 60 * 60;
    private long refreshTime = 3 * 24 * 60 * 60;


    private final TokenGenerator tokenGenerator;
    private final TokenRepository tokenRepository;

    public AbstractAuthenticator(TokenGenerator tokenGenerator, TokenRepository tokenRepository) {
        this.tokenGenerator = tokenGenerator;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Authorize create(Authorize authorize) {
        String token = this.createToken(authorize.getUid());
        authorize.setToken(token);

        this.saveToken(token, authorize);
        this.postCreate(authorize);
        return authorize;
    }

    protected abstract void postCreate(Authorize authorize);

    @Override
    public Authorize get(String token) {
        String tokenKey = this.generateTokenKey(token);
        return tokenRepository.getAuthorize(tokenKey);
    }

    @Override
    public void refresh(String token) {
        Authorize authorize = this.get(token);
        if (authorize == null) {
            return;
        }
        long expire = tokenRepository.getExpire(token);
        if (expire > 0 && expire < this.refreshTime) {
            this.saveToken(token, authorize);
        }
    }

    @Override
    public void delete(String token) {
        Authorize authorize = this.get(token);
        if(authorize == null){
            return;
        }
        String tokenKey = this.generateTokenKey(token);
        tokenRepository.deleteAuthorize(tokenKey);
        this.postDelete(authorize);
    }

    protected abstract void postDelete(Authorize authorize);

    protected String createToken(Long uid) {
        return tokenGenerator.generate(uid.toString());
    }

    protected void saveToken(String token, Authorize authorize) {
        String tokenKey = this.generateTokenKey(token);
        tokenRepository.setAuthorize(tokenKey, authorize, this.expireTime);
    }

    protected String generateTokenKey(String token) {
        return this.keyPrefix + token;
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

    public TokenGenerator getTokenGenerator() {
        return tokenGenerator;
    }

    public TokenRepository getTokenRepository() {
        return tokenRepository;
    }

}
