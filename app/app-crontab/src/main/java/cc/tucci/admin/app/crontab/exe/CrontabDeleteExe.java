package cc.tucci.admin.app.crontab.exe;

import cc.tucci.admin.app.crontab.job.JobUtils;
import cc.tucci.admin.domain.crontab.service.CrontabService;
import cc.tucci.admin.domain.core.dto.Response;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tucci
 */
@Service
public class CrontabDeleteExe {

    private final Scheduler scheduler;
    private final CrontabService crontabService;

    public CrontabDeleteExe(Scheduler scheduler,
                            CrontabService crontabService) {
        this.scheduler = scheduler;
        this.crontabService = crontabService;
    }

    @Transactional
    public Response execute(Long id) {
        crontabService.delete(id);
        JobUtils.deleteJob(scheduler, id.toString());
        return Response.success();
    }
}
