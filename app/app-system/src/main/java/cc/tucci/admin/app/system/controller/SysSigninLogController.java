package cc.tucci.admin.app.system.controller;

import cc.tucci.admin.app.system.service.SysSigninLogAppService;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.app.system.dto.query.SysLoginLogQuery;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tucci
 */
@RestController
@RequestMapping("sys/log/signin")
public class SysSigninLogController {

    private final SysSigninLogAppService sysSigninLogAppService;

    public SysSigninLogController(SysSigninLogAppService sysSigninLogAppService) {
        this.sysSigninLogAppService = sysSigninLogAppService;
    }

    /**
     * 查询登录日志列表
     */
    @RequiresPermissions(value = {"log:signin:list"})
    @GetMapping
    public Response list(SysLoginLogQuery query){
        return sysSigninLogAppService.list(query);
    }
}
