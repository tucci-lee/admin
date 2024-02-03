package cc.tucci.admin.domain.crontab.convertor;

import cc.tucci.admin.domain.crontab.dataobject.CrontabLogDO;
import cc.tucci.admin.domain.crontab.entity.CrontabLog;

/**
 * @author tucci
 */
public class CrontabLogConvertor {

    public static CrontabLogDO toCreate(CrontabLog entity) {
        return new CrontabLogDO()
                .setCrontabId(entity.getCrontabId())
                .setStartTime(entity.getStartTime())
                .setCreateTime(System.currentTimeMillis());
    }

    public static CrontabLogDO toUpdate(CrontabLog entity) {
        return new CrontabLogDO()
                .setId(entity.getId())
                .setStatus(entity.getStatus())
                .setRunTime(entity.getRunTime())
                .setMessage(entity.getMessage());
    }
}
