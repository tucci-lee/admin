package cc.tucci.admin.app.system.service;

import cc.tucci.admin.app.system.dto.body.SysDeptCreateBody;
import cc.tucci.admin.app.system.dto.body.SysDeptUpdateBody;
import cc.tucci.admin.app.system.exe.SysDeptCreateExe;
import cc.tucci.admin.app.system.exe.SysDeptDeleteExe;
import cc.tucci.admin.app.system.exe.SysDeptTreeExe;
import cc.tucci.admin.app.system.exe.SysDeptUpdateExe;
import cc.tucci.admin.domain.core.dto.Response;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SysDeptAppService {

    private final SysDeptTreeExe sysDeptTreeExe;
    private final SysDeptCreateExe sysDeptCreateExe;
    private final SysDeptDeleteExe sysDeptDeleteExe;
    private final SysDeptUpdateExe sysDeptUpdateExe;

    public SysDeptAppService(SysDeptTreeExe sysDeptTreeExe,
                             SysDeptCreateExe sysDeptCreateExe,
                             SysDeptDeleteExe sysDeptDeleteExe,
                             SysDeptUpdateExe sysDeptUpdateExe) {
        this.sysDeptTreeExe = sysDeptTreeExe;
        this.sysDeptCreateExe = sysDeptCreateExe;
        this.sysDeptDeleteExe = sysDeptDeleteExe;
        this.sysDeptUpdateExe = sysDeptUpdateExe;
    }

    public Response tree() {
        return sysDeptTreeExe.execute();
    }

    public Response create(SysDeptCreateBody body) {
        return sysDeptCreateExe.execute(body);
    }

    public Response delete(Long id) {
       return sysDeptDeleteExe.execute(id);
    }

    public Response update(SysDeptUpdateBody body) {
        return sysDeptUpdateExe.execute(body);
    }
}
