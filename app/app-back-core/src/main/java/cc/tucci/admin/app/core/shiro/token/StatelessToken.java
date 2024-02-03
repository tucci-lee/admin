package cc.tucci.admin.app.core.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author tucci
 */
public class StatelessToken implements AuthenticationToken {
    private final String token;

    public StatelessToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
