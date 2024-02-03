package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.assembler.SysRoleAssembler;
import cc.tucci.admin.app.system.dto.vo.SysRoleVO;
import cc.tucci.admin.domain.core.dto.PageResponse;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.system.dataobject.SysRoleDO;
import cc.tucci.admin.app.system.dto.query.SysRoleQuery;
import cc.tucci.admin.domain.system.mapper.SysRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci
 */
@Service
public class SysRoleListExe {

    private final SysRoleMapper sysRoleMapper;

    public SysRoleListExe(SysRoleMapper sysRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
    }

    public Response execute(SysRoleQuery query) {
        Page<SysRoleDO> page = new Page<>(query.getPageNo(), query.getPageSize());
        LambdaQueryWrapper<SysRoleDO> wrapper = Wrappers.lambdaQuery(SysRoleDO.class)
                .likeLeft(!StringUtils.isEmpty(query.getName()), SysRoleDO::getName, query.getName())
                .eq(SysRoleDO::getIsDeleted, false)
                .orderByDesc(SysRoleDO::getCreateTime);
        sysRoleMapper.selectPage(page, wrapper);
        List<SysRoleVO> voList = page.getRecords().stream()
                .map(SysRoleAssembler::toVO)
                .collect(Collectors.toList());
        return PageResponse.success(voList, (int) page.getTotal());
    }
}
