package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.dto.body.SysUserUpdateBody;
import cc.tucci.admin.domain.authorize.Authorize;
import cc.tucci.admin.domain.authorize.AuthorizeContextHolder;
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
public class SysUserUpdateExe {

    private final SysUserService sysUserService;
    private final SysAuthorizeService sysAuthorizeService;

    public SysUserUpdateExe(SysUserService sysUserService,
                            SysAuthorizeService sysAuthorizeService) {
        this.sysUserService = sysUserService;
        this.sysAuthorizeService = sysAuthorizeService;
    }

    public Response execute(SysUserUpdateBody body) {
        // 只有管理员可以修改管理员信息
        if (SysUser.isAdmin(body.getUid())) {
            Authorize authorize = AuthorizeContextHolder.get();
            Long uid = authorize.getUid();

            Assert.isTrue(SysUser.isAdmin(uid), BizCode.NOT_OPERATE_ADMIN);
            Assert.isTrue(body.getIsLock() == null || !body.getIsLock(), BizCode.NOT_OPERATE_ADMIN);
        }

        SysUser update = new SysUser();
        BeanUtils.copyProperties(body, update);
        sysUserService.update(update);
        if (body.getIsLock() != null && body.getIsLock()) {
            sysAuthorizeService.delete(body.getUid());
        }
        return Response.success();
    }
}
