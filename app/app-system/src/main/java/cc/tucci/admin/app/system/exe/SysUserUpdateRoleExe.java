package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.dto.body.SysUserUpdateRoleBody;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.system.entity.SysUser;
import cc.tucci.admin.domain.system.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysUserUpdateRoleExe {

    private final SysUserService sysUserService;

    public SysUserUpdateRoleExe(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public Response execute(SysUserUpdateRoleBody body) {
        SysUser update = new SysUser();
        BeanUtils.copyProperties(body, update);
        sysUserService.updateRole(update);
        return Response.success();
    }
}
