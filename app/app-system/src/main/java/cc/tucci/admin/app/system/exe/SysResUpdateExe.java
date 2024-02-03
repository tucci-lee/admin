package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.dto.body.SysResUpdateBody;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.system.entity.SysRes;
import cc.tucci.admin.domain.system.service.SysResService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysResUpdateExe {

    private final SysResService sysResService;

    public SysResUpdateExe(SysResService sysResService) {
        this.sysResService = sysResService;
    }

    public Response execute(SysResUpdateBody body) {
        SysRes update = new SysRes();
        BeanUtils.copyProperties(body, update);
        sysResService.update(update);
        return Response.success();
    }
}
