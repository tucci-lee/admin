package cc.tucci.admin.app.crontab.assembler;

import cc.tucci.admin.app.crontab.dto.vo.CrontabLogVO;
import cc.tucci.admin.domain.crontab.dataobject.CrontabLogDO;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci
 */
public class CrontabLogAssembler {

    public static CrontabLogVO toVO(CrontabLogDO db) {
        if (db == null) {
            return null;
        }
        CrontabLogVO vo = new CrontabLogVO();
        BeanUtils.copyProperties(db, vo);
        return vo;
    }
}
