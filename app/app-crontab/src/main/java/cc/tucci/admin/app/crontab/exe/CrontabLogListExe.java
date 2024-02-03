package cc.tucci.admin.app.crontab.exe;

import cc.tucci.admin.app.crontab.assembler.CrontabLogAssembler;
import cc.tucci.admin.app.crontab.dto.vo.CrontabLogVO;
import cc.tucci.admin.domain.core.dto.PageResponse;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.crontab.dataobject.CrontabLogDO;
import cc.tucci.admin.app.crontab.dto.query.CrontabLogQuery;
import cc.tucci.admin.domain.crontab.mapper.CrontabLogMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci
 */
@Service
public class CrontabLogListExe {

    private final CrontabLogMapper crontabLogMapper;

    public CrontabLogListExe(CrontabLogMapper crontabLogMapper) {
        this.crontabLogMapper = crontabLogMapper;
    }

    public Response execute(CrontabLogQuery query) {
        Page<CrontabLogDO> page = new Page<>(query.getPageNo(), query.getPageSize());
        LambdaQueryWrapper<CrontabLogDO> wrapper = Wrappers.lambdaQuery(CrontabLogDO.class)
                .eq(CrontabLogDO::getCrontabId, query.getCrontabId())
                .orderByDesc(CrontabLogDO::getCreateTime);
        crontabLogMapper.selectPage(page, wrapper);
        List<CrontabLogVO> voList = page.getRecords().stream()
                .map(CrontabLogAssembler::toVO)
                .collect(Collectors.toList());
        return PageResponse.success(voList, (int) page.getTotal());
    }
}
