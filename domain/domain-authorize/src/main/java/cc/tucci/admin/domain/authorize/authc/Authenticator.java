package cc.tucci.admin.domain.authorize.authc;


import cc.tucci.admin.domain.authorize.Authorize;

/**
 * @author tucci
 */
public interface Authenticator {

    /**
     * 创建一个授权（token等信息）
     *
     * @param authorize authorize
     */
    Authorize create(Authorize authorize);

    /**
     * 获取授权信息
     *
     * @param token token
     * @return 授权信息
     */
    Authorize get(String token);

    /**
     * 刷新授权信息
     *
     * @param token token
     */
    void refresh(String token);

    /**
     * 删除授权信息
     *
     * @param token token
     */
    void delete(String token);

}
