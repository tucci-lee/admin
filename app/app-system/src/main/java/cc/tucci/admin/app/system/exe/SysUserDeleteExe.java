package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.domain.core.exception.BizCode;
import cc.tucci.admin.domain.system.entity.SysUser;
import cc.tucci.admin.domain.system.service.SysUserService;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.core.exception.Assert;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysUserDeleteExe {

    private final SysUserService sysUserService;

    public SysUserDeleteExe(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public Response execute(Long uid) {
        // 不可以删除管理员
        Assert.isTrue(!SysUser.isAdmin(uid), BizCode.NOT_OPERATE_ADMIN);

        sysUserService.delete(uid);
        return Response.success();
    }
}
