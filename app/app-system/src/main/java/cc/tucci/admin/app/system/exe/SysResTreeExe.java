package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.assembler.SysResAssembler;
import cc.tucci.admin.app.system.dto.vo.SysResVO;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.core.dto.SingletonResponse;
import cc.tucci.admin.domain.system.entity.SysRes;
import cc.tucci.admin.domain.system.service.SysResService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci
 */
@Service
public class SysResTreeExe {

    private final SysResService sysResService;

    public SysResTreeExe(SysResService sysResService) {
        this.sysResService = sysResService;
    }

    public Response execute() {
        List<SysRes> entityList = sysResService.listAll();
        List<SysResVO> voList = entityList.stream()
                .map(SysResAssembler::toVO)
                .collect(Collectors.toList());

        List<SysResVO> tree = SysResVO.tree(voList, SysRes.TOP_ID);
        return SingletonResponse.success(tree);
    }
}
