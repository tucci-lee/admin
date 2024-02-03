package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.dto.body.SysUserCreateBody;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.system.entity.SysUser;
import cc.tucci.admin.domain.system.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysUserCreateExe {

    private final SysUserService sysUserService;

    public SysUserCreateExe(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public Response execute(SysUserCreateBody body) {
        SysUser create = new SysUser();
        BeanUtils.copyProperties(body, create);
        sysUserService.create(create);
        return Response.success();
    }
}
