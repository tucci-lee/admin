package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.domain.system.service.SysResService;
import cc.tucci.admin.domain.core.dto.Response;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysResDeleteExe {

    private final SysResService sysResService;

    public SysResDeleteExe(SysResService sysResService) {
        this.sysResService = sysResService;
    }

    public Response execute(Long id) {
        sysResService.delete(id);
        return Response.success();
    }
}
