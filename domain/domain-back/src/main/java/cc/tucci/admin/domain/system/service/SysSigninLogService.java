package cc.tucci.admin.domain.system.service;

import cc.tucci.admin.domain.system.entity.SysSigninLog;

/**
 * @author tucci
 */
public interface SysSigninLogService {

    /**
     * 添加登录日志
     *
     * @param entity 登录日志
     */
    void create(SysSigninLog entity);

}
