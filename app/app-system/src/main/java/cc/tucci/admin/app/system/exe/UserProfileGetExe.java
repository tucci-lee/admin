package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.assembler.SysUserAssembler;
import cc.tucci.admin.app.system.dto.vo.UserProfileVO;
import cc.tucci.admin.domain.authorize.Authorize;
import cc.tucci.admin.domain.authorize.AuthorizeContextHolder;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.core.dto.SingletonResponse;
import cc.tucci.admin.domain.system.entity.SysUser;
import cc.tucci.admin.domain.system.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @author tucci
 */
@Service
public class UserProfileGetExe {

    private final SysUserService sysUserService;

    public UserProfileGetExe(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public Response execute() {
        Authorize authorize = AuthorizeContextHolder.get();
        Long uid = authorize.getUid();

        SysUser entity = sysUserService.getByUid(uid);
        UserProfileVO vo = SysUserAssembler.toProfileVO(entity);
        return SingletonResponse.success(vo);
    }
}
