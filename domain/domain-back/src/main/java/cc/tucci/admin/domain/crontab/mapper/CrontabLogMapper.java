package cc.tucci.admin.domain.crontab.mapper;

import cc.tucci.admin.domain.crontab.dataobject.CrontabLogDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tucci
 */
@Mapper
public interface CrontabLogMapper extends BaseMapper<CrontabLogDO> {

}
