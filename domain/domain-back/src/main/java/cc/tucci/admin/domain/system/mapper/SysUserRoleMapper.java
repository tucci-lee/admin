package cc.tucci.admin.domain.system.mapper;

import cc.tucci.admin.domain.system.dataobject.SysUserRoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tucci
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleDO> {

    int insertList(@Param("uid") Long uid, @Param("roleIds") List<Long> roleIds);

}
