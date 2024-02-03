package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.dto.body.PasswordUpdateBody;
import cc.tucci.admin.domain.authorize.Authorize;
import cc.tucci.admin.domain.authorize.AuthorizeContextHolder;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.system.entity.SysUser;
import cc.tucci.admin.domain.system.service.SysAuthorizeService;
import cc.tucci.admin.domain.system.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class UserPasswordUpdateExe {

    private final SysUserService sysUserService;
    private final SysAuthorizeService sysAuthorizeService;

    public UserPasswordUpdateExe(SysUserService sysUserService,
                                 SysAuthorizeService sysAuthorizeService) {
        this.sysUserService = sysUserService;
        this.sysAuthorizeService = sysAuthorizeService;
    }

    public Response execute(PasswordUpdateBody body) {
        Authorize authorize = AuthorizeContextHolder.get();
        Long uid = authorize.getUid();

        SysUser entity = sysUserService.getByUid(uid);
        entity.verifyPassword(body.getOldPassword());

        SysUser update = new SysUser()
                .setUid(uid)
                .setPassword(body.getPassword());
        sysUserService.updatePassword(update);

        sysAuthorizeService.delete(uid);
        return Response.success();
    }
}
