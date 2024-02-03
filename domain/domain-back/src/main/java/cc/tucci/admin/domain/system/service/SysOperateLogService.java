package cc.tucci.admin.domain.system.service;

import cc.tucci.admin.domain.system.entity.SysOperateLog;

/**
 * @author tucci
 */
public interface SysOperateLogService {

    /**
     * 添加操作日志
     *
     * @param entity 操作日志
     */
    void create(SysOperateLog entity);

}
