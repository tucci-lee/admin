package cc.tucci.admin.domain.authorize.authc;

import cc.tucci.admin.domain.authorize.exception.TooManyLoginException;
import cc.tucci.admin.domain.authorize.token.TokenGenerator;
import cc.tucci.admin.domain.authorize.token.repository.TokenRepository;
import cc.tucci.admin.domain.authorize.Authorize;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tucci
 */
public class DefaultAuthenticator extends AbstractAuthenticator implements MultipleAuthenticator {

    private int onlineCount = -1;
    private boolean kickedOut = true;

    public DefaultAuthenticator(TokenGenerator tokenGenerator, TokenRepository tokenRepository) {
        super(tokenGenerator, tokenRepository);
    }

    @Override
    protected void postCreate(Authorize authorize) {
        TokenRepository tokenRepository = super.getTokenRepository();

        String token = authorize.getToken();
        // 获取用户所有登录的token
        String key = this.generateTokenKey(authorize.getUid());
        List<String> tokens = tokenRepository.getTokens(key);
        // 如果用户一次也未登录过
        if (CollectionUtils.isEmpty(tokens)) {
            tokens = new ArrayList<>();
        }

        // 不限制同时在线
        if (onlineCount <= 0) {
            tokens.add(token);
            tokenRepository.setTokens(key, tokens, super.getExpireTime());
            return;
        }

        // 判断同时登录次数
        int size = tokens.size();
        if (size >= onlineCount && kickedOut) {
            // 如最用户最多登录5次变成了最多3次登录，要将之前多余的3个删除
            int num = tokens.size() - onlineCount + 1;
            for (int i = 0; i < num; i++) {
                tokenRepository.deleteAuthorize(super.generateTokenKey(tokens.get(i)));
            }
            // 由于ArrayList.subList返回的是SubList，反序列化无法解析
            tokens = new ArrayList<>(tokens.subList(num, tokens.size()));
            tokens.add(token);
        } else if (size >= onlineCount) {
            // 不踢出任何用户，当前登录token失效
            tokenRepository.deleteAuthorize(super.generateTokenKey(token));
            throw new TooManyLoginException("登录次数过多，禁止登陆");
        } else {
            tokens.add(token);
        }
        tokenRepository.setTokens(key, tokens, super.getExpireTime());
    }

    /**
     * 删除tokens中的无效授权信息
     *
     * @param authorize 授权信息
     */
    @Override
    protected void postDelete(Authorize authorize) {
        Long uid = authorize.getUid();
        TokenRepository tokenRepository = super.getTokenRepository();
        String key = this.generateTokenKey(uid);
        List<String> tokens = tokenRepository.getTokens(key);
        tokens.remove(authorize.getToken());
        if (tokens.size() == 0) {
            tokenRepository.deleteTokens(key);
        } else {
            tokenRepository.setTokens(key, tokens, super.getExpireTime());
        }
    }

    @Override
    public List<String> get(Long uid) {
        TokenRepository tokenRepository = super.getTokenRepository();
        String key = this.generateTokenKey(uid);
        return tokenRepository.getTokens(key);
    }

    @Override
    public void delete(Long uid) {
        List<String> tokens = this.get(uid);
        if (tokens == null || tokens.isEmpty()) {
            return;
        }
        for (String token : tokens) {
            super.delete(token);
        }
    }

    protected String generateTokenKey(Long uid) {
        return super.getKeyPrefix() + "uid:" + uid;
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
}
