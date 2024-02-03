package cc.tucci.admin.domain.authorize.authc;

import java.util.List;

/**
 * @author tucci
 */
public interface MultipleAuthenticator extends Authenticator {

    /**
     * 获取用户生成的所有token
     *
     * @param uid 用户标识
     * @return token
     */
    List<String> get(Long uid);

    /**
     * 删除用生成的所有token
     *
     * @param uid 用户标识
     */
    void delete(Long uid);
}
