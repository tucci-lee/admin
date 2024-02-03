package cc.tucci.admin.app.system.service;

import cc.tucci.admin.app.system.dto.body.SysUserCreateBody;
import cc.tucci.admin.app.system.dto.body.SysUserUpdateBody;
import cc.tucci.admin.app.system.dto.body.SysUserUpdatePasswordBody;
import cc.tucci.admin.app.system.dto.body.SysUserUpdateRoleBody;
import cc.tucci.admin.app.system.exe.SysRoleListIdByUidExe;
import cc.tucci.admin.app.system.exe.SysUserCreateExe;
import cc.tucci.admin.app.system.exe.SysUserDeleteExe;
import cc.tucci.admin.app.system.exe.SysUserListExe;
import cc.tucci.admin.app.system.exe.SysUserUpdateExe;
import cc.tucci.admin.app.system.exe.SysUserUpdatePasswordExe;
import cc.tucci.admin.app.system.exe.SysUserUpdateRoleExe;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.app.system.dto.query.SysUserQuery;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysUserAppService {

    private final SysUserListExe sysUserListExe;
    private final SysUserCreateExe sysUserCreateExe;
    private final SysUserUpdateExe sysUserUpdateExe;
    private final SysUserUpdatePasswordExe sysUserUpdatePasswordExe;
    private final SysUserDeleteExe sysUserDeleteExe;
    private final SysRoleListIdByUidExe sysRoleListIdByUidExe;
    private final SysUserUpdateRoleExe sysUserUpdateRoleExe;

    public SysUserAppService(SysUserListExe sysUserListExe,
                             SysUserCreateExe sysUserCreateExe,
                             SysUserUpdateExe sysUserUpdateExe,
                             SysUserUpdatePasswordExe sysUserUpdatePasswordExe,
                             SysUserDeleteExe sysUserDeleteExe,
                             SysRoleListIdByUidExe sysRoleListIdByUidExe,
                             SysUserUpdateRoleExe sysUserUpdateRoleExe) {
        this.sysUserListExe = sysUserListExe;
        this.sysUserCreateExe = sysUserCreateExe;
        this.sysUserUpdateExe = sysUserUpdateExe;
        this.sysUserUpdatePasswordExe = sysUserUpdatePasswordExe;
        this.sysUserDeleteExe = sysUserDeleteExe;
        this.sysRoleListIdByUidExe = sysRoleListIdByUidExe;
        this.sysUserUpdateRoleExe = sysUserUpdateRoleExe;
    }

    public Response list(SysUserQuery query) {
        return sysUserListExe.execute(query);
    }

    public Response create(SysUserCreateBody body) {
        return sysUserCreateExe.execute(body);
    }

    public Response update(SysUserUpdateBody body) {
        return sysUserUpdateExe.execute(body);
    }

    public Response updatePassword(SysUserUpdatePasswordBody body) {
        return sysUserUpdatePasswordExe.execute(body);
    }

    public Response delete(Long uid) {
        return sysUserDeleteExe.execute(uid);
    }

    public Response listRoleIdByUid(Long uid) {
        return sysRoleListIdByUidExe.execute(uid);
    }

    public Response updateRole(SysUserUpdateRoleBody body) {
        return sysUserUpdateRoleExe.execute(body);
    }

}
