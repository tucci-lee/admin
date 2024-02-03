package cc.tucci.admin.domain.system.service;

import cc.tucci.admin.domain.authorize.Authorize;
import cc.tucci.admin.domain.system.entity.SysUser;

import java.util.List;

/**
 * @author tucci
 */
public interface SysAuthorizeService {

    /**
     * 创建token
     *
     * @param account  账号信息
     * @param password 用户输入的密码
     * @return token信息
     */
    Authorize create(SysUser account, String password);

    /**
     * 获取授权信息
     *
     * @param token token
     * @return 授权信息
     */
    Authorize get(String token);

    /**
     * 刷新token
     *
     * @param token token信息
     */
    void refresh(String token);

    /**
     * 删除token
     *
     * @param token token信息
     */
    void delete(String token);

    List<String> get(Long uid);

    void delete(Long uid);

}
