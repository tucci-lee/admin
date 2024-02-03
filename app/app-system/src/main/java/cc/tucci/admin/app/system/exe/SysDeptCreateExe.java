package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.dto.body.SysDeptCreateBody;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.system.entity.SysDept;
import cc.tucci.admin.domain.system.service.SysDeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysDeptCreateExe {

    private final SysDeptService sysDeptService;

    public SysDeptCreateExe(SysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }

    public Response execute(SysDeptCreateBody body) {
        SysDept create = new SysDept();
        BeanUtils.copyProperties(body, create);
        sysDeptService.create(create);
        return Response.success();
    }
}
