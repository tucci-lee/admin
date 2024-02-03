package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.assembler.LogOperateAssembler;
import cc.tucci.admin.app.system.dto.vo.SysOperateLogVO;
import cc.tucci.admin.domain.core.dto.PageResponse;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.system.dataobject.SysOperateLogDO;
import cc.tucci.admin.app.system.dto.query.SysOperateLogQuery;
import cc.tucci.admin.domain.system.mapper.SysOperateLogMapper;
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
public class SysOperateLogListExe {

    private final SysOperateLogMapper sysOperateLogMapper;

    public SysOperateLogListExe(SysOperateLogMapper sysOperateLogMapper) {
        this.sysOperateLogMapper = sysOperateLogMapper;
    }

    public Response execute(SysOperateLogQuery query) {
        Page<SysOperateLogDO> page = new Page<>(query.getPageNo(), query.getPageSize());
        LambdaQueryWrapper<SysOperateLogDO> wrapper = Wrappers.lambdaQuery(SysOperateLogDO.class)
                .eq(!StringUtils.isEmpty(query.getUsername()), SysOperateLogDO::getUsername, query.getUsername())
                .eq(!StringUtils.isEmpty(query.getIp()), SysOperateLogDO::getIp, query.getIp())
                .eq(query.getStatus() != null, SysOperateLogDO::getStatus, query.getStatus())
                .gt(query.getBeginTime() != null, SysOperateLogDO::getCreateTime, query.getBeginTime())
                .lt(query.getEndTime() != null, SysOperateLogDO::getCreateTime, query.getEndTime())
                .orderByDesc(SysOperateLogDO::getCreateTime);
        sysOperateLogMapper.selectPage(page, wrapper);
        List<SysOperateLogVO> voList = page.getRecords().stream()
                .map(LogOperateAssembler::toVO)
                .collect(Collectors.toList());
        return PageResponse.success(voList, (int) page.getTotal());
    }
}
