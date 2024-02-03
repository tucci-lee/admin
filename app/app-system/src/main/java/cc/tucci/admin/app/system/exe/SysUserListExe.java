package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.assembler.SysUserAssembler;
import cc.tucci.admin.app.system.dto.vo.SysUserVO;
import cc.tucci.admin.domain.core.dto.PageResponse;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.system.dataobject.SysUserDO;
import cc.tucci.admin.domain.system.entity.SysDept;
import cc.tucci.admin.app.system.dto.query.SysUserQuery;
import cc.tucci.admin.domain.system.mapper.SysUserMapper;
import cc.tucci.admin.domain.system.service.SysDeptService;
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
public class SysUserListExe {

    private final SysUserMapper sysUserMapper;
    private final SysDeptService sysDeptService;

    public SysUserListExe(SysUserMapper sysUserMapper,
                          SysDeptService sysDeptService) {
        this.sysUserMapper = sysUserMapper;
        this.sysDeptService = sysDeptService;
    }

    public Response execute(SysUserQuery query) {
        Page<SysUserDO> page = new Page<>(query.getPageNo(), query.getPageSize());
        LambdaQueryWrapper<SysUserDO> wrapper = Wrappers.lambdaQuery(SysUserDO.class)
                .likeLeft(!StringUtils.isEmpty(query.getUsername()), SysUserDO::getUsername, query.getUsername())
                .likeLeft(!StringUtils.isEmpty(query.getPhone()), SysUserDO::getPhone, query.getPhone())
                .eq(query.getIsLock() != null, SysUserDO::getIsLock, query.getIsLock())
                .eq(query.getDeptId() != null, SysUserDO::getDeptId, query.getDeptId())
                .eq(SysUserDO::getIsDeleted, false)
                .orderByDesc(SysUserDO::getCreateTime);

        sysUserMapper.selectPage(page, wrapper);

        List<SysUserVO> voList = page.getRecords().stream().map(e -> {
            String deptName = null;
            if (e.getDeptId() != null) {
                SysDept dept = sysDeptService.getById(e.getDeptId());
                deptName = dept == null ? null : dept.getName();
            }
            return SysUserAssembler.toVO(e, deptName);
        }).collect(Collectors.toList());
        return PageResponse.success(voList, (int) page.getTotal());
    }
}
