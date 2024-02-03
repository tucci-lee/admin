package cc.tucci.admin.app.system.assembler;

import cc.tucci.admin.app.system.dto.vo.SysLoginLogVO;
import cc.tucci.admin.domain.system.dataobject.SysSigninLogDO;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci
 */
public class LogLoginAssembler {

    public static SysLoginLogVO toVO(SysSigninLogDO db) {
        if (db == null) {
            return null;
        }
        SysLoginLogVO vo = new SysLoginLogVO();
        BeanUtils.copyProperties(db, vo);
        return vo;
    }
}
