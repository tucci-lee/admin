package cc.tucci.admin.app.crontab.service;

import cc.tucci.admin.app.crontab.dto.body.CrontabCreateBody;
import cc.tucci.admin.app.crontab.dto.body.CrontabUpdateBody;
import cc.tucci.admin.app.crontab.dto.body.CrontabUpdateStatusBody;
import cc.tucci.admin.app.crontab.exe.CrontabCreateExe;
import cc.tucci.admin.app.crontab.exe.CrontabDeleteExe;
import cc.tucci.admin.app.crontab.exe.CrontabInitExe;
import cc.tucci.admin.app.crontab.exe.CrontabListExe;
import cc.tucci.admin.app.crontab.exe.CrontabStartExe;
import cc.tucci.admin.app.crontab.exe.CrontabUpdateExe;
import cc.tucci.admin.app.crontab.exe.CrontabUpdateStatusExe;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.app.crontab.dto.query.CrontabQuery;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class CrontabAppService implements InitializingBean {

    private final CrontabInitExe crontabInitExe;
    private final CrontabListExe crontabListExe;
    private final CrontabCreateExe crontabCreateExe;
    private final CrontabUpdateExe crontabUpdateExe;
    private final CrontabDeleteExe crontabDeleteExe;
    private final CrontabUpdateStatusExe crontabUpdateStatusExe;
    private final CrontabStartExe crontabStartExe;

    public CrontabAppService(CrontabInitExe crontabInitExe,
                             CrontabListExe crontabListExe,
                             CrontabCreateExe crontabCreateExe,
                             CrontabUpdateExe crontabUpdateExe,
                             CrontabDeleteExe crontabDeleteExe,
                             CrontabUpdateStatusExe crontabUpdateStatusExe,
                             CrontabStartExe crontabStartExe) {
        this.crontabInitExe = crontabInitExe;
        this.crontabListExe = crontabListExe;
        this.crontabCreateExe = crontabCreateExe;
        this.crontabUpdateExe = crontabUpdateExe;
        this.crontabDeleteExe = crontabDeleteExe;
        this.crontabUpdateStatusExe = crontabUpdateStatusExe;
        this.crontabStartExe = crontabStartExe;
    }

    /**
     * 将所有的定时任务加载到内存中
     */
    @Override
    public void afterPropertiesSet() {
        crontabInitExe.execute();
    }

    public Response list(CrontabQuery query) {
        return crontabListExe.execute(query);
    }

    public Response create(CrontabCreateBody body) {
        return crontabCreateExe.execute(body);
    }

    public Response update(CrontabUpdateBody body) {
        return crontabUpdateExe.execute(body);
    }

    public Response delete(Long id) {
        return crontabDeleteExe.execute(id);
    }

    public Response updateStatus(CrontabUpdateStatusBody body) {
        return crontabUpdateStatusExe.execute(body);
    }

    public Response start(Long id) {
        return crontabStartExe.execute(id);
    }

}
