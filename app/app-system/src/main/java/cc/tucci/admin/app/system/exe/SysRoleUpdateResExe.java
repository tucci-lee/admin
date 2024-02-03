package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.dto.body.SysRoleUpdateResBody;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.system.entity.SysRole;
import cc.tucci.admin.domain.system.service.SysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysRoleUpdateResExe {

    private final SysRoleService sysRoleService;

    public SysRoleUpdateResExe(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    public Response execute(SysRoleUpdateResBody body) {
        SysRole update = new SysRole();
        BeanUtils.copyProperties(body, update);
        sysRoleService.updateRes(update);
        return Response.success();
    }
}
