package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.dto.body.SysResCreateBody;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.system.entity.SysRes;
import cc.tucci.admin.domain.system.service.SysResService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysResCreateExe {

    private final SysResService sysResService;

    public SysResCreateExe(SysResService sysResService) {
        this.sysResService = sysResService;
    }

    public Response execute(SysResCreateBody body) {
        SysRes create = new SysRes();
        BeanUtils.copyProperties(body, create);
        sysResService.create(create);
        return Response.success();
    }
}
