package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.assembler.SysDeptAssembler;
import cc.tucci.admin.app.system.dto.vo.SysDeptVO;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.core.dto.SingletonResponse;
import cc.tucci.admin.domain.system.entity.SysDept;
import cc.tucci.admin.domain.system.service.SysDeptService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci
 */
@Service
public class SysDeptTreeExe {

    private final SysDeptService sysDeptService;

    public SysDeptTreeExe(SysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }

    public Response execute() {
        List<SysDept> entityList = sysDeptService.listAll();
        List<SysDeptVO> voList = entityList.stream()
                .map(SysDeptAssembler::toVO)
                .collect(Collectors.toList());

        List<SysDeptVO> tree = SysDeptVO.tree(voList, SysDept.TOP_ID);
        return SingletonResponse.success(tree);
    }


}
