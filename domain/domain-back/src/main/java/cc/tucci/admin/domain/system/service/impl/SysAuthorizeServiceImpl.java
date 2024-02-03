package cc.tucci.admin.domain.system.service.impl;

import cc.tucci.admin.domain.authorize.Authorize;
import cc.tucci.admin.domain.authorize.authc.Authenticator;
import cc.tucci.admin.domain.authorize.authc.MultipleAuthenticator;
import cc.tucci.admin.domain.core.exception.Assert;
import cc.tucci.admin.domain.core.exception.BizCode;
import cc.tucci.admin.domain.core.exception.BizException;
import cc.tucci.admin.domain.system.entity.SysUser;
import cc.tucci.admin.domain.system.service.SysAuthorizeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tucci
 */
@Service
public class SysAuthorizeServiceImpl implements SysAuthorizeService {

    private final Authenticator authenticator;

    public SysAuthorizeServiceImpl(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public Authorize create(SysUser account, String password) {
        Assert.notNull(account, BizCode.ACCOUNT_OR_PASSWORD_ERROR);
        account.verifyPassword(password);
        account.verifyLock();

        Authorize authorize = Authorize.build(account.getUid());
        try {
            return authenticator.create(authorize);
        } catch (RuntimeException e) {
            throw new BizException(BizCode.PROHIBITED_LOGIN);
        }
    }

    @Override
    public Authorize get(String token) {
        return authenticator.get(token);
    }

    @Override
    public void refresh(String token) {
        authenticator.refresh(token);
    }

    @Override
    public void delete(String token) {
        authenticator.delete(token);
    }

    @Override
    public List<String> get(Long uid) {
        if (authenticator instanceof MultipleAuthenticator) {
            MultipleAuthenticator multipleAuthenticator = (MultipleAuthenticator) authenticator;
            return multipleAuthenticator.get(uid);
        }
        return null;
    }

    @Override
    public void delete(Long uid) {
        if (authenticator instanceof MultipleAuthenticator) {
            MultipleAuthenticator multipleAuthenticator = (MultipleAuthenticator) authenticator;
            multipleAuthenticator.delete(uid);
        }
    }
}
