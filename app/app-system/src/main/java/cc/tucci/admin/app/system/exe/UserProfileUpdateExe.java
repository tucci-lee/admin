package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.dto.body.ProfileUpdateBody;
import cc.tucci.admin.domain.authorize.Authorize;
import cc.tucci.admin.domain.authorize.AuthorizeContextHolder;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.system.entity.SysUser;
import cc.tucci.admin.domain.system.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class UserProfileUpdateExe {

    private final SysUserService sysUserService;

    public UserProfileUpdateExe(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public Response execute(ProfileUpdateBody body) {
        Authorize authorize = AuthorizeContextHolder.get();
        Long uid = authorize.getUid();

        SysUser update = new SysUser()
                .setUid(uid)
                .setNickname(body.getNickname())
                .setPhone(body.getPhone())
                .setEmail(body.getEmail());
        sysUserService.update(update);
        return Response.success();
    }
}
