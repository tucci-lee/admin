package cc.tucci.admin.app.system.controller;

import cc.tucci.admin.app.system.dto.query.SysOperateLogQuery;
import cc.tucci.admin.app.system.service.SysOperateLogAppService;
import cc.tucci.admin.domain.core.dto.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tucci
 */
@RestController
@RequestMapping("sys/log/operate")
public class SysOperateLogController {

    private final SysOperateLogAppService sysOperateLogAppService;

    public SysOperateLogController(SysOperateLogAppService sysOperateLogAppService) {
        this.sysOperateLogAppService = sysOperateLogAppService;
    }

    /**
     * 查询操作日志列表
     */
    @RequiresPermissions(value = {"log:operate:list"})
    @GetMapping
    public Response list(SysOperateLogQuery query){
        return sysOperateLogAppService.list(query);
    }
}
