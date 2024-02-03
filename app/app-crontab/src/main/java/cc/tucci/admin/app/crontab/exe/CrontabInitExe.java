package cc.tucci.admin.app.crontab.exe;

import cc.tucci.admin.app.crontab.job.JobUtils;
import cc.tucci.admin.domain.crontab.entity.Crontab;
import cc.tucci.admin.domain.crontab.service.CrontabService;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tucci
 */
@Service
public class CrontabInitExe {

    private final Scheduler scheduler;
    private final CrontabService crontabService;

    public CrontabInitExe(Scheduler scheduler,
                          CrontabService crontabService) {
        this.scheduler = scheduler;
        this.crontabService = crontabService;
    }

    public void execute() {
        List<Crontab> crontabs = crontabService.all();
        for (Crontab crontab : crontabs) {
            JobUtils.createJob(scheduler, crontab);
        }
    }
}
