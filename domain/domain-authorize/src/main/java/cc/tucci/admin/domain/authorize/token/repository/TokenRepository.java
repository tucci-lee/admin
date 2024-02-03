package cc.tucci.admin.domain.authorize.token.repository;

import cc.tucci.admin.domain.authorize.Authorize;

import java.util.List;

/**
 * @author tucci
 */
public interface TokenRepository {

    /**
     * 设置授权信息
     *
     * @param key       key
     * @param authorize 授权信息
     * @param expire    过期时间/秒
     */
    void setAuthorize(String key, Authorize authorize, long expire);

    /**
     * 设置所有授权信息的token
     *
     * @param key    key
     * @param tokens 所有token
     * @param expire 过期时间/秒
     */
    void setTokens(String key, List<String> tokens, long expire);

    /**
     * 获取授权信息
     *
     * @param key key
     * @return 授权信息
     */
    Authorize getAuthorize(String key);

    /**
     * 获取所有授权信息的token
     *
     * @param key key
     * @return 所有授权信息的token
     */
    List<String> getTokens(String key);

    /**
     * 删除授权信息
     *
     * @param key key
     */
    void deleteAuthorize(String key);

    /**
     * 删除所有授权信息的token
     *
     * @param key key
     */
    void deleteTokens(String key);

    /**
     * 获取key的过期时间
     *
     * @param key key
     * @return 过期时间
     */
    long getExpire(String key);

}
