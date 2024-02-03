package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.domain.authorize.Authorize;
import cc.tucci.admin.domain.authorize.AuthorizeContextHolder;
import cc.tucci.admin.domain.system.service.SysAuthorizeService;
import cc.tucci.admin.domain.core.dto.Response;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class SignoutExe {

    private final SysAuthorizeService sysAuthorizeService;

    public SignoutExe(SysAuthorizeService sysAuthorizeService) {
        this.sysAuthorizeService = sysAuthorizeService;
    }

    public Response execute() {
        Authorize authorize = AuthorizeContextHolder.get();
        sysAuthorizeService.delete(authorize.getToken());
        return Response.success();
    }
}
