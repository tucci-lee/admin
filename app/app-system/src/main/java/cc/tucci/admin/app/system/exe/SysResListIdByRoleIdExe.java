package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.domain.system.entity.SysRes;
import cc.tucci.admin.domain.system.service.SysResService;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.core.dto.SingletonResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci
 */
@Service
public class SysResListIdByRoleIdExe {

    private final SysResService sysResService;

    public SysResListIdByRoleIdExe(SysResService sysResService) {
        this.sysResService = sysResService;
    }

    public Response execute(Long roleId){
        List<Long> resIds = sysResService.listByRoleId(roleId)
                .stream()
                .map(SysRes::getId)
                .collect(Collectors.toList());
        return SingletonResponse.success(resIds);
    }
}
