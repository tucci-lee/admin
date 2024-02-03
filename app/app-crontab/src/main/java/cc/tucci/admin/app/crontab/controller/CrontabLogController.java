package cc.tucci.admin.app.crontab.controller;

import cc.tucci.admin.app.crontab.service.CrontabLogAppService;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.app.crontab.dto.query.CrontabLogQuery;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tucci
 */
@RestController
@RequestMapping("crontab/log")
public class CrontabLogController {

    private final CrontabLogAppService crontabLogAppService;

    public CrontabLogController(CrontabLogAppService crontabLogAppService) {
        this.crontabLogAppService = crontabLogAppService;
    }

    @RequiresPermissions(value = {"crontab:list"})
    @GetMapping
    public Response list(@Validated CrontabLogQuery query){
        return crontabLogAppService.list(query);
    }
}
