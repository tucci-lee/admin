package cc.tucci.admin.app.system.exe;

import cc.tucci.admin.app.system.assembler.LogLoginAssembler;
import cc.tucci.admin.app.system.dto.vo.SysLoginLogVO;
import cc.tucci.admin.domain.core.dto.PageResponse;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.system.dataobject.SysSigninLogDO;
import cc.tucci.admin.app.system.dto.query.SysLoginLogQuery;
import cc.tucci.admin.domain.system.mapper.SysSigninLogMapper;
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
public class SysSigninLogListExe {

    private final SysSigninLogMapper sysSigninLogMapper;

    public SysSigninLogListExe(SysSigninLogMapper sysSigninLogMapper) {
        this.sysSigninLogMapper = sysSigninLogMapper;
    }

    public Response execute(SysLoginLogQuery query) {
        Page<SysSigninLogDO> page = new Page<>(query.getPageNo(), query.getPageSize());
        LambdaQueryWrapper<SysSigninLogDO> wrapper = Wrappers.lambdaQuery(SysSigninLogDO.class)
                .eq(!StringUtils.isEmpty(query.getUsername()), SysSigninLogDO::getUsername, query.getUsername())
                .eq(!StringUtils.isEmpty(query.getIp()), SysSigninLogDO::getIp, query.getIp())
                .eq(query.getStatus() != null, SysSigninLogDO::getStatus, query.getStatus())
                .gt(query.getBeginTime() != null, SysSigninLogDO::getCreateTime, query.getBeginTime())
                .lt(query.getEndTime() != null, SysSigninLogDO::getCreateTime, query.getEndTime())
                .orderByDesc(SysSigninLogDO::getCreateTime);
        sysSigninLogMapper.selectPage(page, wrapper);
        List<SysLoginLogVO> voList = page.getRecords().stream()
                .map(LogLoginAssembler::toVO)
                .collect(Collectors.toList());
        return PageResponse.success(voList, (int) page.getTotal());
    }
}
