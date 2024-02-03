package cc.tucci.admin.app.crontab.exe;

import cc.tucci.admin.app.crontab.dto.body.CrontabCreateBody;
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
public class CrontabCreateExe {

    private final Scheduler scheduler;
    private final CrontabService crontabService;

    public CrontabCreateExe(Scheduler scheduler,
                            CrontabService crontabService) {
        this.scheduler = scheduler;
        this.crontabService = crontabService;
    }

    @Transactional
    public Response execute(CrontabCreateBody body) {
        Crontab crontab = new Crontab()
                .setStatus(false);
        BeanUtils.copyProperties(body, crontab);
        Long id = crontabService.create(crontab);

        crontab.setId(id);
        JobUtils.createJob(scheduler, crontab);

        return Response.success();
    }
}
