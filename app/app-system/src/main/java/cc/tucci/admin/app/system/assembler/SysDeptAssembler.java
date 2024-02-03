package cc.tucci.admin.app.system.assembler;

import cc.tucci.admin.app.system.dto.vo.SysDeptVO;
import cc.tucci.admin.domain.system.entity.SysDept;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci
 */
public class SysDeptAssembler {

    public static SysDeptVO toVO(SysDept entity) {
        if(entity == null){
            return null;
        }
        SysDeptVO vo = new SysDeptVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
