package cc.tucci.admin.domain.system.service.impl;

import cc.tucci.admin.domain.system.convertor.LogLoginConvertor;
import cc.tucci.admin.domain.system.dataobject.SysSigninLogDO;
import cc.tucci.admin.domain.system.entity.SysSigninLog;
import cc.tucci.admin.domain.system.mapper.SysSigninLogMapper;
import cc.tucci.admin.domain.system.service.SysSigninLogService;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysSigninLogServiceImpl implements SysSigninLogService {
    private final SysSigninLogMapper sysSigninLogMapper;

    public SysSigninLogServiceImpl(SysSigninLogMapper sysSigninLogMapper) {
        this.sysSigninLogMapper = sysSigninLogMapper;
    }

    @Override
    public void create(SysSigninLog entity) {
        SysSigninLogDO create = LogLoginConvertor.toCreate(entity);
        sysSigninLogMapper.insert(create);
    }

}
