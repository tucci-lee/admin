package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.dto.body.SysDeptUpdateBody;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.system.entity.SysDept;
import cc.tucci.admin.domain.system.service.SysDeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysDeptUpdateExe {

    private final SysDeptService sysDeptService;

    public SysDeptUpdateExe(SysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }

    public Response execute(SysDeptUpdateBody body) {
        SysDept update = new SysDept();
        BeanUtils.copyProperties(body, update);
        sysDeptService.update(update);
        return Response.success();
    }
}
