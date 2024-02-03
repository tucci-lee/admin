package cc.tucci.admin.app.system.service;

import cc.tucci.admin.app.system.exe.SysOperateLogListExe;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.app.system.dto.query.SysOperateLogQuery;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysOperateLogAppService {

    private final SysOperateLogListExe sysOperateLogListExe;

    public SysOperateLogAppService( SysOperateLogListExe sysOperateLogListExe) {
        this.sysOperateLogListExe = sysOperateLogListExe;
    }

    public Response list(SysOperateLogQuery query) {
        return sysOperateLogListExe.execute(query);
    }
}
