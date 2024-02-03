package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.domain.system.service.SysDeptService;
import cc.tucci.admin.domain.core.dto.Response;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysDeptDeleteExe {

    private final SysDeptService sysDeptService;

    public SysDeptDeleteExe(SysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }

    public Response execute(Long id) {
        sysDeptService.delete(id);
        return Response.success();
    }
}
