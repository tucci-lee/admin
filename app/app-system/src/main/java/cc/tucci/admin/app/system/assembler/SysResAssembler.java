package cc.tucci.admin.app.system.assembler;

import cc.tucci.admin.app.system.dto.vo.SysResVO;
import cc.tucci.admin.domain.system.entity.SysRes;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci
 */
public class SysResAssembler {

    public static SysResVO toVO(SysRes entity) {
        if(entity == null){
            return null;
        }
        SysResVO vo = new SysResVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
