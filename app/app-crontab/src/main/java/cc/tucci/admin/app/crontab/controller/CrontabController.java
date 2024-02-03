package cc.tucci.admin.app.crontab.controller;

import cc.tucci.admin.app.core.aspect.Operate;
import cc.tucci.admin.app.crontab.dto.body.CrontabCreateBody;
import cc.tucci.admin.app.crontab.dto.body.CrontabUpdateBody;
import cc.tucci.admin.app.crontab.dto.body.CrontabUpdateStatusBody;
import cc.tucci.admin.app.crontab.service.CrontabAppService;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.app.crontab.dto.query.CrontabQuery;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author tucci
 */
@RestController
@RequestMapping("crontab")
public class CrontabController {

    private final CrontabAppService crontabAppService;

    public CrontabController(CrontabAppService crontabAppService) {
        this.crontabAppService = crontabAppService;
    }

    /**
     * 查询定时任务列表
     */
    @RequiresPermissions(value = {"crontab:list"})
    @GetMapping
    public Response list(CrontabQuery query) {
        return crontabAppService.list(query);
    }

    /**
     * 添加定时任务
     *
     */
    @Operate("添加定时任务")
    @RequiresPermissions(value = {"crontab:create"})
    @PostMapping
    public Response create(@Valid @RequestBody CrontabCreateBody body) {
        return crontabAppService.create(body);
    }

    /**
     * 修改定时任务
     */
    @Operate("修改定时任务")
    @RequiresPermissions(value = {"crontab:update"})
    @PutMapping
    public Response update(@Valid @RequestBody CrontabUpdateBody body){
        return crontabAppService.update(body);
    }

    /**
     * 删除定时任务
     */
    @Operate("删除定时任务")
    @RequiresPermissions(value = {"crontab:delete"})
    @DeleteMapping("{id}")
    public Response delete(@PathVariable Long id){
        return crontabAppService.delete(id);
    }

    @Operate("编辑定时任务状态")
    @RequiresPermissions(value = {"crontab:update:status"})
    @PutMapping("status")
    public Response updateStatus(@Valid @RequestBody CrontabUpdateStatusBody body) {
        return crontabAppService.updateStatus(body);
    }

    @Operate("执行定时任务")
    @RequiresPermissions(value = {"crontab:start"})
    @PostMapping("start/{id}")
    public Response start(@PathVariable Long id){
        return crontabAppService.start(id);
    }
}
