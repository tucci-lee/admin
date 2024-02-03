package cc.tucci.admin.app.system.service;

import cc.tucci.admin.app.system.exe.SysSigninLogListExe;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.app.system.dto.query.SysLoginLogQuery;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysSigninLogAppService {

    private final SysSigninLogListExe sysSigninLogListExe;

    public SysSigninLogAppService(SysSigninLogListExe sysSigninLogListExe) {
        this.sysSigninLogListExe = sysSigninLogListExe;
    }

    public Response list(SysLoginLogQuery query) {
        return sysSigninLogListExe.execute(query);
    }
}
