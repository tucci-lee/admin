package cc.tucci.admin.app.crontab.exe;

import cc.tucci.admin.app.crontab.job.JobUtils;
import cc.tucci.admin.domain.crontab.entity.Crontab;
import cc.tucci.admin.domain.crontab.service.CrontabService;
import cc.tucci.admin.domain.core.dto.Response;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class CrontabStartExe {

    private final Scheduler scheduler;
    private final CrontabService crontabService;

    public CrontabStartExe(Scheduler scheduler,
                           CrontabService crontabService) {
        this.scheduler = scheduler;
        this.crontabService = crontabService;
    }

    public Response execute(Long id) {
        Crontab crontab = crontabService.getById(id);
        JobUtils.startJob(scheduler, crontab.getId().toString());
        return Response.success();
    }
}
