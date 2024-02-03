package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.domain.system.service.SysRoleService;
import cc.tucci.admin.domain.core.dto.Response;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysRoleDeleteExe {

    private final SysRoleService sysRoleService;

    public SysRoleDeleteExe(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    public Response execute(Long id) {
        sysRoleService.delete(id);
        return Response.success();
    }
}
