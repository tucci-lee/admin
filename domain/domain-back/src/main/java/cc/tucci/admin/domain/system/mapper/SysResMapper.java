package cc.tucci.admin.domain.system.mapper;

import cc.tucci.admin.domain.system.dataobject.SysResDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tucci
 */
@Mapper
public interface SysResMapper extends BaseMapper<SysResDO> {

    List<SysResDO> selectByUid(Long uid);

    List<SysResDO> selectByRoleId(Long roleId);
}