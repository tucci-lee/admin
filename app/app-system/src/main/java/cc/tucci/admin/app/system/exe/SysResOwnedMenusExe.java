package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.assembler.SysResAssembler;
import cc.tucci.admin.app.system.dto.vo.SysResVO;
import cc.tucci.admin.domain.authorize.Authorize;
import cc.tucci.admin.domain.authorize.AuthorizeContextHolder;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.core.dto.SingletonResponse;
import cc.tucci.admin.domain.system.entity.SysRes;
import cc.tucci.admin.domain.system.entity.SysUser;
import cc.tucci.admin.domain.system.service.SysAuthorizeService;
import cc.tucci.admin.domain.system.service.SysResService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author tucci
 */
@Service
public class SysResOwnedMenusExe {

    private final SysResService sysResService;
    private final SysAuthorizeService sysAuthorizeService;

    public SysResOwnedMenusExe(SysResService sysResService,
                               SysAuthorizeService sysAuthorizeService) {
        this.sysResService = sysResService;
        this.sysAuthorizeService = sysAuthorizeService;
    }

    public Response execute() {
        Authorize authorize = AuthorizeContextHolder.get();
        Long uid = authorize.getUid();
        List<SysRes> entityList;
        // 如果是admin用户则返回所有菜单
        if (SysUser.isAdmin(uid)) {
            entityList = sysResService.listAll()
                    .stream()
                    .filter(e -> Objects.equals(SysRes.Type.MENU, e.getType()))
                    .collect(Collectors.toList());
        } else {
            entityList = sysResService.listByUid(uid)
                    .stream()
                    .filter(e -> Objects.equals(SysRes.Type.MENU, e.getType()))
                    .collect(Collectors.toList());
        }
        List<SysResVO> voList = entityList.stream()
                .map(SysResAssembler::toVO)
                .collect(Collectors.toList());

        List<SysResVO> tree = SysResVO.tree(voList, SysRes.TOP_ID);

        // 刷新token
        sysAuthorizeService.refresh(authorize.getToken());
        return SingletonResponse.success(tree);
    }


}
