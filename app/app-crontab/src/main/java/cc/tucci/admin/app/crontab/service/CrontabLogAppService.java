package cc.tucci.admin.app.crontab.service;

import cc.tucci.admin.app.crontab.exe.CrontabLogListExe;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.app.crontab.dto.query.CrontabLogQuery;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class CrontabLogAppService {

    private final CrontabLogListExe crontabLogListExe;

    public CrontabLogAppService(CrontabLogListExe crontabLogListExe) {
        this.crontabLogListExe = crontabLogListExe;
    }

    public Response list(CrontabLogQuery query) {
        return crontabLogListExe.execute(query);
    }
}
