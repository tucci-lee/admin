package cc.tucci.admin.domain.system.mapper;

import cc.tucci.admin.domain.system.dataobject.SysRoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tucci
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleDO> {

    List<SysRoleDO> selectByUid(Long uid);
}
