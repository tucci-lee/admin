package cc.tucci.admin.app.system.assembler;

import cc.tucci.admin.app.system.dto.vo.SysOperateLogVO;
import cc.tucci.admin.domain.system.dataobject.SysOperateLogDO;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci
 */
public class LogOperateAssembler {

    public static SysOperateLogVO toVO(SysOperateLogDO db) {
        if (db == null) {
            return null;
        }
        SysOperateLogVO vo = new SysOperateLogVO();
        BeanUtils.copyProperties(db, vo);
        return vo;
    }
}
