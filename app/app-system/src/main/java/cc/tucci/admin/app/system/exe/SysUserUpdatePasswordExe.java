package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.dto.body.SysUserUpdatePasswordBody;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.core.exception.Assert;
import cc.tucci.admin.domain.core.exception.BizCode;
import cc.tucci.admin.domain.system.entity.SysUser;
import cc.tucci.admin.domain.system.service.SysAuthorizeService;
import cc.tucci.admin.domain.system.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysUserUpdatePasswordExe {

    private final SysUserService sysUserService;
    private final SysAuthorizeService sysAuthorizeService;

    public SysUserUpdatePasswordExe(SysUserService sysUserService,
                                    SysAuthorizeService sysAuthorizeService) {
        this.sysUserService = sysUserService;
        this.sysAuthorizeService = sysAuthorizeService;
    }

    public Response execute(SysUserUpdatePasswordBody body) {
        // 不可以修改管理员密码
        Assert.isTrue(!SysUser.isAdmin(body.getUid()), BizCode.NOT_OPERATE_ADMIN);

        SysUser update = new SysUser();
        BeanUtils.copyProperties(body, update);
        sysUserService.updatePassword(update);

        sysAuthorizeService.delete(body.getUid());

        return Response.success();
    }
}
