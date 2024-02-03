package cc.tucci.admin.app.system.controller;

import cc.tucci.admin.app.core.aspect.Operate;
import cc.tucci.admin.app.system.dto.body.SysDeptCreateBody;
import cc.tucci.admin.app.system.dto.body.SysDeptUpdateBody;
import cc.tucci.admin.app.system.service.SysDeptAppService;
import cc.tucci.admin.domain.core.dto.Response;
import org.apache.shiro.authz.annotation.Logical;
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
@RequestMapping("sys/dept")
public class SysDeptController {

    private final SysDeptAppService sysDeptAppService;

    public SysDeptController(SysDeptAppService sysDeptAppService) {
        this.sysDeptAppService = sysDeptAppService;
    }

    /**
     * 查询部门树
     */
    @RequiresPermissions(value = {"sys:dept:tree", "sys:user:list"}, logical = Logical.OR)
    @GetMapping("tree")
    public Response tree() {
        return sysDeptAppService.tree();
    }

    /**
     * 添加部门
     */
    @Operate("添加部门")
    @RequiresPermissions(value = {"sys:dept:create"})
    @PostMapping
    public Response create(@Valid @RequestBody SysDeptCreateBody body) {
        return sysDeptAppService.create(body);
    }

    /**
     * 删除部门
     */
    @Operate("删除部门")
    @RequiresPermissions(value = {"sys:dept:delete"})
    @DeleteMapping("{id}")
    public Response delete(@PathVariable Long id) {
        return sysDeptAppService.delete(id);
    }

    /**
     * 修改部门
     */
    @Operate("修改部门")
    @RequiresPermissions(value = {"sys:dept:update"})
    @PutMapping
    public Response update(@Valid @RequestBody SysDeptUpdateBody body) {
        return sysDeptAppService.update(body);
    }

}
