package cc.tucci.admin.app.crontab.assembler;

import cc.tucci.admin.app.crontab.dto.vo.CrontabVO;
import cc.tucci.admin.domain.crontab.dataobject.CrontabDO;
import org.springframework.beans.BeanUtils;

/**
 * @author tucci
 */
public class CrontabAssembler {

    public static CrontabVO toVO(CrontabDO db) {
        if (db == null) {
            return null;
        }
        CrontabVO vo = new CrontabVO();
        BeanUtils.copyProperties(db, vo);
        return vo;
    }
}
