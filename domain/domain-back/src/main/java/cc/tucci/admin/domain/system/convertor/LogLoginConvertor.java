package cc.tucci.admin.domain.system.convertor;

import cc.tucci.admin.domain.system.dataobject.SysSigninLogDO;
import cc.tucci.admin.domain.system.entity.SysSigninLog;

/**
 * @author tucci
 */
public class LogLoginConvertor {

    public static SysSigninLogDO toCreate(SysSigninLog entity) {
        return new SysSigninLogDO()
                .setUsername(entity.getUsername())
                .setOs(entity.getOs())
                .setBrowser(entity.getBrowser())
                .setIp(entity.getIp())
                .setStatus(entity.getStatus())
                .setMessage(entity.getMessage())
                .setCreateTime(System.currentTimeMillis());
    }
}
