package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.dto.body.SysRoleUpdateBody;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.system.entity.SysRole;
import cc.tucci.admin.domain.system.service.SysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysRoleUpdateExe {

    private final SysRoleService sysRoleService;

    public SysRoleUpdateExe(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    public Response execute(SysRoleUpdateBody body) {
        SysRole update = new SysRole();
        BeanUtils.copyProperties(body, update);
        sysRoleService.update(update);
        return Response.success();
    }
}
