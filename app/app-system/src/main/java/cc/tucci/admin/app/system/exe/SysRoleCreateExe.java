package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.dto.body.SysRoleCreateBody;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.system.entity.SysRole;
import cc.tucci.admin.domain.system.service.SysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysRoleCreateExe {

    private final SysRoleService sysRoleService;

    public SysRoleCreateExe(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    public Response execute(SysRoleCreateBody body) {
        SysRole create = new SysRole();
        BeanUtils.copyProperties(body, create);
        sysRoleService.create(create);
        return Response.success();
    }
}
