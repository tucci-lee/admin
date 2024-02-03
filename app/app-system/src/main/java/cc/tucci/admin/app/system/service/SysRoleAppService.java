package cc.tucci.admin.app.system.service;

import cc.tucci.admin.app.system.dto.body.SysRoleCreateBody;
import cc.tucci.admin.app.system.dto.body.SysRoleUpdateBody;
import cc.tucci.admin.app.system.dto.body.SysRoleUpdateResBody;
import cc.tucci.admin.app.system.exe.SysResListIdByRoleIdExe;
import cc.tucci.admin.app.system.exe.SysRoleCreateExe;
import cc.tucci.admin.app.system.exe.SysRoleDeleteExe;
import cc.tucci.admin.app.system.exe.SysRoleListExe;
import cc.tucci.admin.app.system.exe.SysRoleUpdateExe;
import cc.tucci.admin.app.system.exe.SysRoleUpdateResExe;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.app.system.dto.query.SysRoleQuery;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysRoleAppService {

    private final SysRoleListExe sysRoleListExe;
    private final SysRoleCreateExe sysRoleCreateExe;
    private final SysRoleUpdateExe sysRoleUpdateExe;
    private final SysRoleDeleteExe sysRoleDeleteExe;
    private final SysResListIdByRoleIdExe sysResListIdByRoleIdExe;
    private final SysRoleUpdateResExe sysRoleUpdateResExe;

    public SysRoleAppService(SysRoleListExe sysRoleListExe,
                             SysRoleCreateExe sysRoleCreateExe,
                             SysRoleUpdateExe sysRoleUpdateExe,
                             SysRoleDeleteExe sysRoleDeleteExe,
                             SysResListIdByRoleIdExe sysResListIdByRoleIdExe,
                             SysRoleUpdateResExe sysRoleUpdateResExe) {
        this.sysRoleListExe = sysRoleListExe;
        this.sysRoleCreateExe = sysRoleCreateExe;
        this.sysRoleUpdateExe = sysRoleUpdateExe;
        this.sysRoleDeleteExe = sysRoleDeleteExe;
        this.sysResListIdByRoleIdExe = sysResListIdByRoleIdExe;
        this.sysRoleUpdateResExe = sysRoleUpdateResExe;
    }

    public Response list(SysRoleQuery query) {
        return sysRoleListExe.execute(query);
    }

    public Response create(SysRoleCreateBody body) {
        return sysRoleCreateExe.execute(body);
    }

    public Response update(SysRoleUpdateBody body) {
        return sysRoleUpdateExe.execute(body);
    }

    public Response delete(Long id) {
        return sysRoleDeleteExe.execute(id);
    }

    public Response listResIdById(Long id) {
        return sysResListIdByRoleIdExe.execute(id);
    }

    public Response updateRes(SysRoleUpdateResBody body) {
        return sysRoleUpdateResExe.execute(body);
    }

}
