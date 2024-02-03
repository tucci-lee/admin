package cc.tucci.admin.domain.system.convertor;

import cc.tucci.admin.domain.system.dataobject.SysOperateLogDO;
import cc.tucci.admin.domain.system.entity.SysOperateLog;

/**
 * @author tucci
 */
public class LogOperateConvertor {

    public static SysOperateLogDO toCreate(SysOperateLog entity) {
        return new SysOperateLogDO()
                .setUsername(entity.getUsername())
                .setIp(entity.getIp())
                .setUrl(entity.getUrl())
                .setMethod(entity.getMethod())
                .setParams(entity.getParams())
                .setResult(entity.getResult())
                .setDescription(entity.getDescription())
                .setErrorMessage(entity.getErrorMessage())
                .setStatus(entity.getStatus())
                .setCreateTime(System.currentTimeMillis());
    }
}
