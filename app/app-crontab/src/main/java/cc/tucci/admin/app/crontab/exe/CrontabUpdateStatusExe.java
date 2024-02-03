package cc.tucci.admin.app.crontab.exe;

import cc.tucci.admin.app.crontab.dto.body.CrontabUpdateStatusBody;
import cc.tucci.admin.app.crontab.job.JobUtils;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.crontab.entity.Crontab;
import cc.tucci.admin.domain.crontab.service.CrontabService;
import org.quartz.Scheduler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tucci
 */
@Service
public class CrontabUpdateStatusExe {

    private final Scheduler scheduler;
    private final CrontabService crontabService;

    public CrontabUpdateStatusExe(Scheduler scheduler,
                                  CrontabService crontabService) {
        this.scheduler = scheduler;
        this.crontabService = crontabService;
    }

    @Transactional
    public Response execute(CrontabUpdateStatusBody body) {
        Crontab crontab = new Crontab();
        BeanUtils.copyProperties(body, crontab);
        crontabService.updateStatus(crontab);

        if (crontab.getStatus()) {
            JobUtils.resumeJob(scheduler, body.getId().toString());
        } else {
            JobUtils.pauseJob(scheduler, body.getId().toString());
        }
        return Response.success();
    }
}
