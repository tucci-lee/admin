package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.domain.system.entity.SysRole;
import cc.tucci.admin.domain.system.service.SysRoleService;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.core.dto.SingletonResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci
 */
@Service
public class SysRoleListIdByUidExe {

    private final SysRoleService sysRoleService;

    public SysRoleListIdByUidExe(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    public Response execute(Long uid){
        List<Long> roleIds = sysRoleService.listByUid(uid)
                .stream()
                .map(SysRole::getId)
                .collect(Collectors.toList());
        return SingletonResponse.success(roleIds);
    }
}
