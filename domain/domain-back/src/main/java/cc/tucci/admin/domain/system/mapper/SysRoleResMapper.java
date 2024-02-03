package cc.tucci.admin.domain.system.mapper;

import cc.tucci.admin.domain.system.dataobject.SysRoleResDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tucci
 */
@Mapper
public interface SysRoleResMapper extends BaseMapper<SysRoleResDO> {

    int insertList(@Param("roleId") Long roleId, @Param("resIds") List<Long> resIds);

}
