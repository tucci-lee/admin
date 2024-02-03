package cc.tucci.admin.app.crontab.job;

import cc.tucci.admin.domain.crontab.service.CrontabLogService;
import org.quartz.JobExecutionContext;

/**
 * @author tucci
 */
public class TestJob extends LogJob {
    protected TestJob(CrontabLogService crontabLogService) {
        super(crontabLogService);
    }

    @Override
    protected void doExecute(JobExecutionContext context) throws Exception {
        System.out.println(1);
    }
}
