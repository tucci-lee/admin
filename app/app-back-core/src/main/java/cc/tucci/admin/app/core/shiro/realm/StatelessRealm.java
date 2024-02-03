package cc.tucci.admin.app.core.shiro.realm;

import cc.tucci.admin.app.core.shiro.token.StatelessToken;
import cc.tucci.admin.domain.authorize.Authorize;
import cc.tucci.admin.domain.system.entity.SysRes;
import cc.tucci.admin.domain.system.entity.SysUser;
import cc.tucci.admin.domain.system.service.SysAuthorizeService;
import cc.tucci.admin.domain.system.service.SysResService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci
 */
@Component
public class StatelessRealm extends AuthorizingRealm {
    private final SysResService sysResService;
    private final SysAuthorizeService sysAuthorizeService;

    public StatelessRealm(SysResService sysResService,
                          SysAuthorizeService sysAuthorizeService) {
        this.sysResService = sysResService;
        this.sysAuthorizeService = sysAuthorizeService;
        super.setCredentialsMatcher(new AllowAllCredentialsMatcher());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Authorize authorize = (Authorize) principalCollection.getPrimaryPrincipal();
        long uid = authorize.getUid();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (SysUser.isAdmin(uid)) {
            info.addStringPermission("*");
        } else {
            List<String> resChars = this.sysResService.listByUid(uid).stream()
                    .filter((sysRes) -> sysRes.getResChar() != null && !sysRes.getResChar().isEmpty())
                    .map(SysRes::getResChar)
                    .collect(Collectors.toList());
            info.addStringPermissions(resChars);
        }

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token.getCredentials() == null) {
            throw new AuthenticationException("非法的token");
        }
        String headerToken = (String) token.getCredentials();
        Authorize authorize = this.sysAuthorizeService.get(headerToken);
        if (authorize == null) {
            throw new AuthenticationException("非法的token，无效或过期");
        }
        return new SimpleAuthenticationInfo(authorize, null, this.getName());

    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;
    }
}
