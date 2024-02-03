package cc.tucci.admin.app.system.controller;

import cc.tucci.admin.app.core.aspect.Operate;
import cc.tucci.admin.app.system.dto.body.SysUserCreateBody;
import cc.tucci.admin.app.system.dto.body.SysUserUpdateBody;
import cc.tucci.admin.app.system.dto.body.SysUserUpdatePasswordBody;
import cc.tucci.admin.app.system.dto.body.SysUserUpdateRoleBody;
import cc.tucci.admin.app.system.service.SysUserAppService;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.app.system.dto.query.SysUserQuery;
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
@RequestMapping("sys/user")
public class SysUserController {

    private final SysUserAppService sysUserAppService;

    public SysUserController(SysUserAppService sysUserAppService) {
        this.sysUserAppService = sysUserAppService;
    }

    /**
     * 用户列表查询
     */
    @RequiresPermissions(value = {"sys:user:list"})
    @GetMapping
    public Response list(SysUserQuery query) {
        return sysUserAppService.list(query);
    }

    /**
     * 添加用户
     */
    @Operate("添加用户")
    @RequiresPermissions(value = {"sys:user:create"})
    @PostMapping
    public Response create(@Valid @RequestBody SysUserCreateBody body) {
        return sysUserAppService.create(body);
    }

    /**
     * 修改用户
     */
    @Operate("修改用户")
    @RequiresPermissions(value = {"sys:user:update"})
    @PutMapping
    public Response update(@Valid @RequestBody SysUserUpdateBody body) {
        return sysUserAppService.update(body);
    }

    /**
     * 修改密码
     */
    @Operate("修改用户密码")
    @RequiresPermissions(value = {"sys:user:update:password"})
    @PutMapping("password")
    public Response updatePassword(@Valid @RequestBody SysUserUpdatePasswordBody body) {
        return sysUserAppService.updatePassword(body);
    }

    /**
     * 删除用户
     */
    @Operate("删除用户")
    @RequiresPermissions(value = {"sys:user:delete"})
    @DeleteMapping("{uid}")
    public Response delete(@PathVariable Long uid) {
        return sysUserAppService.delete(uid);
    }


    /**
     * 查询用户关联的角色id
     */
    @RequiresPermissions(value = {"sys:user:list"})
    @GetMapping("role/{uid}")
    public Response listRole(@PathVariable Long uid) {
        return sysUserAppService.listRoleIdByUid(uid);
    }

    /**
     * 修改用户关联的角色
     */
    @Operate("修改用户关联的角色")
    @RequiresPermissions(value = {"sys:user:update"})
    @PutMapping("role")
    public Response updateRole(@Valid @RequestBody SysUserUpdateRoleBody body) {
        return sysUserAppService.updateRole(body);
    }
}
