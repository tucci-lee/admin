package cc.tucci.admin.app.crontab.exe;

import cc.tucci.admin.app.crontab.dto.body.CrontabUpdateBody;
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
public class CrontabUpdateExe {

    private final Scheduler scheduler;
    private final CrontabService crontabService;

    public CrontabUpdateExe(Scheduler scheduler,
                            CrontabService crontabService) {
        this.scheduler = scheduler;
        this.crontabService = crontabService;
    }

    @Transactional
    public Response execute(CrontabUpdateBody body) {
        Crontab oldCrontab = crontabService.getById(body.getId());

        Crontab crontab = new Crontab();
        BeanUtils.copyProperties(body, crontab);
        crontabService.update(crontab);
        crontab.setStatus(oldCrontab.getStatus());

        JobUtils.deleteJob(scheduler, body.getId().toString());
        JobUtils.createJob(scheduler, crontab);

        return Response.success();
    }
}
