package cc.tucci.admin.domain.crontab.mapper;

import cc.tucci.admin.domain.crontab.dataobject.CrontabDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tucci
 */
@Mapper
public interface CrontabMapper extends BaseMapper<CrontabDO> {

}
