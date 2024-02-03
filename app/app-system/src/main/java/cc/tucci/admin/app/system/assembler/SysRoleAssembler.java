package cc.tucci.admin.app.system.assembler;

import cc.tucci.admin.app.system.dto.vo.SysRoleVO;
import cc.tucci.admin.domain.system.dataobject.SysRoleDO;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci
 */
public class SysRoleAssembler {

    public static SysRoleVO toVO(SysRoleDO db) {
        if (db == null) {
            return null;
        }
        SysRoleVO vo = new SysRoleVO();
        BeanUtils.copyProperties(db, vo);
        return vo;
    }
}
