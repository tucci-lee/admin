package cc.tucci.admin.app.system.assembler;

import cc.tucci.admin.app.system.dto.vo.SysUserVO;
import cc.tucci.admin.app.system.dto.vo.UserProfileVO;
import cc.tucci.admin.domain.system.dataobject.SysUserDO;
import cc.tucci.admin.domain.system.entity.SysUser;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci
 */
public class SysUserAssembler {

    public static SysUserVO toVO(SysUserDO db, String deptName) {
        if (db == null) {
            return null;
        }
        SysUserVO vo = new SysUserVO();
        BeanUtils.copyProperties(db, vo);
        vo.setDeptName(deptName);
        return vo;
    }

    public static UserProfileVO toProfileVO(SysUser entity) {
        if (entity == null) {
            return null;
        }
        UserProfileVO vo = new UserProfileVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
