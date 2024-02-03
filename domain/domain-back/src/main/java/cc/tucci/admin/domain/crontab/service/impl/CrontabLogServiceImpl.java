package cc.tucci.admin.domain.crontab.service.impl;

import cc.tucci.admin.domain.crontab.convertor.CrontabLogConvertor;
import cc.tucci.admin.domain.crontab.dataobject.CrontabLogDO;
import cc.tucci.admin.domain.crontab.entity.CrontabLog;
import cc.tucci.admin.domain.crontab.mapper.CrontabLogMapper;
import cc.tucci.admin.domain.crontab.service.CrontabLogService;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class CrontabLogServiceImpl implements CrontabLogService {

    private final CrontabLogMapper crontabLogMapper;

    public CrontabLogServiceImpl(CrontabLogMapper crontabLogMapper) {
        this.crontabLogMapper = crontabLogMapper;
    }

    @Override
    public Long create(CrontabLog entity) {
        CrontabLogDO create = CrontabLogConvertor.toCreate(entity);
        crontabLogMapper.insert(create);
        return create.getId();
    }

    @Override
    public void update(CrontabLog entity) {
        CrontabLogDO update = CrontabLogConvertor.toUpdate(entity);
        crontabLogMapper.updateById(update);
    }
}
