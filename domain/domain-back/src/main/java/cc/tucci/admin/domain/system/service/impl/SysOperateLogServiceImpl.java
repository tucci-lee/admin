package cc.tucci.admin.domain.system.service.impl;

import cc.tucci.admin.domain.system.convertor.LogOperateConvertor;
import cc.tucci.admin.domain.system.dataobject.SysOperateLogDO;
import cc.tucci.admin.domain.system.entity.SysOperateLog;
import cc.tucci.admin.domain.system.mapper.SysOperateLogMapper;
import cc.tucci.admin.domain.system.service.SysOperateLogService;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysOperateLogServiceImpl implements SysOperateLogService {
    
    private final SysOperateLogMapper sysOperateLogMapper;

    public SysOperateLogServiceImpl(SysOperateLogMapper sysOperateLogMapper) {
        this.sysOperateLogMapper = sysOperateLogMapper;
    }

    @Override
    public void create(SysOperateLog entity) {
        SysOperateLogDO create = LogOperateConvertor.toCreate(entity);
        sysOperateLogMapper.insert(create);
    }
}
